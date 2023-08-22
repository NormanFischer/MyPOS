package com.norman.MyPosServer.Transaction;

import com.norman.MyPosServer.Item.ItemRepository;
import com.norman.MyPosServer.User.User;
import com.norman.MyPosServer.User.UserService;
import com.norman.MyPosServer.Item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void saveTransaction(User transactionUser, PostTransactionDTO postTransactionDTO) {
        Transaction toAdd = new Transaction();
        ArrayList<TransactionItem> transactionItems = new ArrayList<>();

        for (TransactionItemDTO transactionitemDTO : postTransactionDTO.getItems()) {
            TransactionItem transactionItem = new TransactionItem();
            Optional<Item> itemRef = itemRepository.getItemBySku(transactionitemDTO.getSku());
            if (itemRef.isEmpty()) {
                System.out.println("Transaction cancelled: Item not found");
                return;
            }
            transactionItem.setItem(itemRef.get());
            transactionItem.setQuantity(transactionitemDTO.getQuantity());
            //TODO: Change
            transactionItem.setTransactionDirection(TransactionDirection.CUSTOMER_PURCHASE);
            transactionItems.add(transactionItem);
        }

        toAdd.setTransactionItems(transactionItems);
        toAdd.setUser(transactionUser);

        if (transactionUser != null) {
            //transactionRepository.save(toAdd);
            System.out.println("Got transaction");
            System.out.println(toAdd.toString());
        } else {
            System.out.println("User id not found, transaction not saved");
        }
    }

}
