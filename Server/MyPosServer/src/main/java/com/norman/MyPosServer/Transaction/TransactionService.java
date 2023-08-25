package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Exceptions.InvalidFilterException;
import com.norman.MyPosServer.Exceptions.SkuNotFoundException;
import com.norman.MyPosServer.Item.ItemQueryType;
import com.norman.MyPosServer.Item.ItemRepository;
import com.norman.MyPosServer.User.User;
import com.norman.MyPosServer.User.UserService;
import com.norman.MyPosServer.Item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              ItemRepository itemRepository,
                              UserService userService) {
        this.transactionRepository = transactionRepository;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public List<TransactionTableEntryDTO> getTransactionsByQuery(String filter, String query) throws IllegalArgumentException {
        System.out.println("Getting transactions by query");
        TransactionQueryType queryTypeObj = TransactionQueryType.valueOf(filter);
        List<Transaction> transactions;
        switch (queryTypeObj) {
            case USER: transactions = transactionRepository.listByUsername(query);
                       break;
            case DATETIME://TODO
                    transactions = new ArrayList<>();
                    break;
            case TOTAL: //TODO
                    transactions = new ArrayList<>();
                    break;
            default:
                throw new InvalidFilterException("Invalid enumerated type with filter: " + filter);
        };

        //Map to DTOS
        return transactions.stream()
                .map(this::toTableEntryDTO)
                .collect(Collectors.toList());
    }

    private TransactionTableEntryDTO toTableEntryDTO(Transaction transaction) {
        return new TransactionTableEntryDTO(transaction.getUser().getUsername(),
                                            "Datetime here!");
    }

    public void saveTransaction(User transactionUser, PostTransactionDTO postTransactionDTO) {
        Transaction toAdd = new Transaction();
        ArrayList<TransactionItem> transactionItems = new ArrayList<>();

        //TODO: Do more than just return

        if (transactionUser == null) {
            System.out.println("User not found");
            return;
        }

        boolean isValidTotal = false;

        try {
            isValidTotal = verifyTotal(postTransactionDTO);
        } catch (SkuNotFoundException e) {
            System.out.println("Sku was not found when verifying total");
            return;
        }

        if (!isValidTotal) {
            System.out.println("Invalid total between client and server: Aborting transaction");
            return;
        }

        for (TransactionItemDTO transactionitemDTO : postTransactionDTO.getItems()) {
            TransactionItem transactionItem = new TransactionItem();
            Optional<Item> itemRef = itemRepository.getItemBySku(transactionitemDTO.getSku());
            if (itemRef.isEmpty()) {
                System.out.println("Transaction cancelled: Item not found");
                return;
            }
            transactionItem.setItem(itemRef.get());
            transactionItem.setQuantity(transactionitemDTO.getQuantity());
            //TODO: Change depending on transaction direction request
            transactionItem.setTransactionDirection(TransactionDirection.CUSTOMER_PURCHASE);
            transactionItem.setTransaction(toAdd);
            transactionItems.add(transactionItem);
        }

        toAdd.setTransactionItems(transactionItems);
        toAdd.setUser(transactionUser);
        toAdd.setDateTime(LocalDateTime.now());

        transactionRepository.save(toAdd);
        System.out.println("Transaction completed");
        System.out.println(toAdd.toString());
    }

    private boolean verifyTotal(PostTransactionDTO postTransactionDTO) throws SkuNotFoundException {
        BigInteger serverTotal = getServerSideTotal(postTransactionDTO);
        return serverTotal.equals(postTransactionDTO.getTotal());
    }

    private BigInteger getServerSideTotal(PostTransactionDTO postTransactionDTO) throws SkuNotFoundException {
        BigInteger runningTotal = BigInteger.valueOf(0);
        for (TransactionItemDTO itemDTO : postTransactionDTO.getItems()) {
            String sku = itemDTO.getSku();
            Optional<BigInteger> itemCost = itemRepository.getCostBySku(sku);
            if (itemCost.isEmpty()) {
                throw new SkuNotFoundException("Invalid subtotal for sku: " + sku);
            } else {
                BigInteger subTotal = itemCost.get().multiply(BigInteger.valueOf(itemDTO.getQuantity()));
                runningTotal = runningTotal.add(subTotal);
            }
        }
        System.out.println("Server Total: " + runningTotal);
        System.out.println("Client Total: " + postTransactionDTO.getTotal());
        return runningTotal;
    }

}
