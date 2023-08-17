package com.norman.MyPosServer.Item;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.norman.MyPosServer.InvalidItemQueryTypeException;
import com.norman.MyPosServer.ItemQueryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createItem(Item item) {
        itemRepository.save(item);
    }

    public boolean deleteItemBySku(String sku) {
        System.out.println("User requested to delete item");
        Optional<Integer> idToDelete = itemRepository.getIDBySku(sku);
        if (idToDelete.isPresent()) {
            itemRepository.deleteById(idToDelete.get());
            System.out.println("Item deleted");
            return true;
        } else {
            System.out.println("User requested to delete but sku was not found");
            return false;
        }
    }

    public Optional<Item> getItemBySku(String sku) {
        Optional<Item> op = itemRepository.getItemBySku(sku);
        return op;
    }

    public List<Item> getItemsByQuery(String filter, String query) throws IllegalArgumentException {
        System.out.println("Get items by query requested!" + filter);
        ItemQueryType queryTypeObj = ItemQueryType.valueOf(filter);
        if (queryTypeObj == null) {
            throw new NullPointerException("Null query type requested");
        }
        switch(queryTypeObj) {
            case SKU:
                return itemRepository.listBySku(query);
            case ITEM_NAME:
                return itemRepository.listByName(query);
            case COST:
                //TODO
                return new ArrayList<Item>();
            default:
                throw new InvalidItemQueryTypeException("Invalid enumerated type with query: " + query);
        }
    }


}
