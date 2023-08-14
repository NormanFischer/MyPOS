package com.norman.MyPosServer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/items")
public class ItemController {

    @GetMapping
    public String redirect() {
        return "/index.html";
    }

    private ItemRepository itemRepository;

    @PostMapping(path="/createItem")
    public @ResponseBody String createItem(
        @RequestBody Item item
    ) {
        itemRepository.save(item);
        return "New item created";
    }

    @DeleteMapping(path="/deleteItem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@RequestParam String sku) {
        System.out.println("User requested to delete item");
        Optional<Integer> idToDelete = itemRepository.getIDBySku(sku);
        if (idToDelete.isPresent()) {
            itemRepository.deleteById(idToDelete.get());
            System.out.println("Item deleted");
            return ResponseEntity.ok("{\"response\": \"Item deleted\"}");
        } else {
            System.out.println("User requested to delete but sku was not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested to delete but sku was not found");
        }
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

    @GetMapping(path="/getItemBySku/{sku}")
    public ResponseEntity<?> getItemBySku(@PathVariable String sku) {
        Optional<Item> op = itemRepository.getItemBySku(sku);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find item with provided sku");
        }
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
