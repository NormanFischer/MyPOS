package com.norman.MyPosServer;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    @GetMapping(path="/getItems/{filter}/{query}")
    public ResponseEntity<?> getItemsByQuery(@PathVariable String filter, @PathVariable String query) {
        switch (filter) {
            case "SKU":
                return ResponseEntity.ok(itemRepository.listBySku(query));
            case "NAME":
                return ResponseEntity.ok(itemRepository.listByName(query));
            case "COST":
                //TODO
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid filter type requested");
        }
    }
}
