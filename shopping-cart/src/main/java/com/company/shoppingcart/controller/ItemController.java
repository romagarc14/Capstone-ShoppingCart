package com.company.shoppingcart.controller;
import com.company.shoppingcart.dto.Receipt;
import com.company.shoppingcart.service.ItemService;
import com.company.shoppingcart.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
    private Receipt receipt;

    @RequestMapping(value="/items", method= RequestMethod.POST)
    public Item addItem(@RequestBody Item item) {
        itemService.addItem(item);
        return item;
    }

    @RequestMapping(value="/items", method=RequestMethod.GET)
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping(value="/items/{id}", method=RequestMethod.GET)
    public Item getItemById(@PathVariable int id) {
        return itemService.getItemById(id);
    }

    @RequestMapping(value="/items/{id}", method=RequestMethod.PUT)
    public Item updateItem(@RequestBody Item item, @PathVariable int id) {
        return itemService.updateItem(item, id);
    }
    @RequestMapping(value="/items/{id}", method=RequestMethod.DELETE)
    public void deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public Receipt purchase(@RequestBody @Valid Item[] item) {
        return itemService.purchase(item);
    }
}