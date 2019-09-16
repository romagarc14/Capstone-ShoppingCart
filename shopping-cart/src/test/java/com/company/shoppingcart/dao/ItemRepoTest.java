package com.company.shoppingcart.dao;
import com.company.shoppingcart.dto.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.containsInAnyOrder;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepoTest {
    @Autowired
    ItemRepository itemRepo;

    Item item1;
    Item item2;
    Item item3;

    @Before
    public void setUp(){
        itemRepo.deleteAll();

        item1 = new Item();
        item1.setName("book");
        item1.setPrice((float) 12.49);
        item1.setCategory("Book");
        item1.setImported(false);
        item1.setQuantity(1);

        item2 = new Item();
        item2.setName("music CD");
        item2.setPrice((float) 14.99);
        item2.setCategory("Music");
        item2.setImported(false);
        item2.setQuantity(1);

        item3 = new Item();
        item3.setName("chocolate bar");
        item3.setPrice((float) .85);
        item3.setCategory("Food");
        item3.setImported(false);
        item3.setQuantity(1);

    }

    @Test
    @Transactional
    public void shouldGetAllItems(){
        itemRepo.save(item1);
        itemRepo.save(item2);
        itemRepo.save(item3);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        List<Item> allItems = itemRepo.findAll();

        assertThat(allItems, containsInAnyOrder(item1, item2, item3));
    }

    @Test
    @Transactional
    public void shouldGetItemById() {
        Item oneItem = itemRepo.save(item2);
        int id = oneItem.getId();

        Item getItem = itemRepo.getOne(id);

        assertEquals(oneItem, getItem);
    }

    @Test
    @Transactional
    public void shouldAddItem() {
        itemRepo.save(item1);
        itemRepo.save(item2);

        assertNotNull(item1.getId());
        assertNotNull(item2.getId());
    }

    @Test
    @Transactional
    public void shouldUpdateItem() {
        Item oneItem = itemRepo.save(item3);
        oneItem.setName("bar of chocolate");

        itemRepo.save(oneItem);

        List<Item> allItems = itemRepo.findAll();

        assertEquals(allItems.size(), 1);
        assertEquals(allItems.get(0).getName(), "bar of chocolate");
    }

    @Test
    @Transactional
    public void shouldDeleteItem() {
        Item oneItem = itemRepo.save(item1);
        itemRepo.deleteById(oneItem.getId());

        List<Item> allItems = itemRepo.findAll();

        assertEquals(allItems.size(), 0);
    }

    @After
    public void tearDown() {
        itemRepo.deleteAll();
    }
}
