package com.norman.MyPosServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping(path="/createItem")
    public @ResponseBody String createItem(
        @RequestParam String sku,
        @RequestParam String name,
        @RequestParam int initialQuantity,
        @RequestParam int cost
    ) {
        Item i = new Item();
        i.setSku(sku);
        i.setItemName(name);
        i.setQuantity(initialQuantity);
        i.setCost(cost);

        itemRepository.save(i);
        return "New item created";
    }

    @GetMapping(path="/getAll")
    public String getAllItems() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Items</h1>");
        if (itemRepository != null) {
            sb.append(itemRepository.findAll());
        }
        return sb.toString();
    }
}
