package com.norman.MyPosServer.Item;

import java.util.List;
import java.util.Optional;

import com.norman.MyPosServer.Exceptions.InvalidFilterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path="/createItem")
    public @ResponseBody String createItem(
        @RequestBody Item item
    ) {
        itemService.createItem(item);
        return "New item created";
    }

    @DeleteMapping(path="/deleteItem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@RequestParam String sku) {
        boolean deleted = itemService.deleteItemBySku(sku);
        if(deleted) {
            return ResponseEntity.ok("{\"response\": \"Item deleted\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested to delete but sku was not found");
    }

    @GetMapping(path="/getItemBySku/{sku}")
    public ResponseEntity<?> getItemBySku(@PathVariable String sku) {
        Optional<Item> op = itemService.getItemBySku(sku);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find item with provided sku");
        }
    }

    @GetMapping(path="/getItems/{filter}/{query}")
    public ResponseEntity<?> getItemsByQuery(@PathVariable String filter, @PathVariable String query) {
        //TODO: Logging
        try {
            List<Item> items = itemService.getItemsByQuery(filter, query);
            return ResponseEntity.ok(items);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid filter type requested");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Null query type requested");
        } catch (InvalidFilterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid item query type requested");
        }
    }
}
