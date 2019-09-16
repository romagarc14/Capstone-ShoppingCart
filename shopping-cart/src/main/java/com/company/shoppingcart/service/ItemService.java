package com.company.shoppingcart.service;

import com.company.shoppingcart.dao.ItemRepository;
import com.company.shoppingcart.dto.Item;
import com.company.shoppingcart.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemService {
    @Autowired
    ItemRepository itemRepo;


    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    public Item getItemById(int id) {
        return itemRepo.getOne(id);
    }

    public Item addItem(Item item){
        return itemRepo.save(item);
    }

    public Item updateItem(Item item, int id) {
        return itemRepo.save(item);
    }


    public void deleteItem(int id) {
        itemRepo.deleteById(id);
    }

    public Receipt purchase(Item[] item) {
        Receipt receipt = new Receipt();
        float total = 0;
        float subtotal = 0;
        float totalTax = 0;

        for (Item i : item) {
            Item currItem = this.getItemById(i.getId());
            currItem.setQuantity(i.getQuantity());
            float price = currItem.getPrice();
            int quantity = currItem.getQuantity();
            boolean isImported = currItem.getImported();
            String category = currItem.getCategory();
            boolean isTaxable = true;
            float cost = price * quantity;

            if(category.equalsIgnoreCase("Book") || category.equalsIgnoreCase("Food") || category.equalsIgnoreCase("Medicine")){
                isTaxable = false;
            }

            if (!isImported && !isTaxable){
                subtotal += cost;

            }
            if (!isImported && isTaxable){
                float x = cost*10;
                double y = Math.ceil(x/5);
                subtotal += cost;
                totalTax += ((y*5)/100);

            }
            if (isImported && isTaxable){
                float x = cost*15;
                double y = Math.ceil(x/5);
                subtotal += cost;
                totalTax += ((y*5)/100);

            }
            if (isImported && !isTaxable){
                double y = Math.ceil(cost);
                subtotal += cost;
                totalTax += ((y*5)/100);

            }
            total = subtotal + totalTax;
        }

        receipt.setSubtotal(subtotal);
        receipt.setTotalTax(totalTax);
        receipt.setTotal(total);
        return receipt;
    }

}



