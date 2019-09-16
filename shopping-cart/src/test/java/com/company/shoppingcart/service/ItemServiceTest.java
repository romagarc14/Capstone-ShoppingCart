package com.company.shoppingcart.service;
import com.company.shoppingcart.dao.ItemRepository;
import com.company.shoppingcart.dto.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
        @Mock
        @Autowired
        ItemRepository itemRepoMock;

        @InjectMocks
        ItemService itemService;

        Item item1;
        Item item2;

        List<Item> itemList;

        @Before
        public void setUp() {

        item1 = new Item();
        item1.setName("box of chocolates");
        item1.setPrice((float) 10.00);
        item1.setCategory("Food");
        item1.setImported(true);
        item1.setQuantity(1);

        item2 = new Item();
        item2.setName("perfume");
        item2.setPrice((float) 47.50);
        item2.setCategory("Luxury");
        item2.setImported(true);
        item2.setQuantity(1);

        itemList = Arrays.asList(item1, item2);
        }

        @Test
        public void shouldGetAllItems() {
                when(itemRepoMock.findAll()).thenReturn(itemList);
                assertEquals(itemList, itemService.getAllItems());
        }

        @Test
        public void shouldGetItemById() {
                when(itemRepoMock.getOne(1)).thenReturn(item1);
                assertEquals(item1, itemService.getItemById(1));
        }

        @Test
        public void shouldAddItem() {
                when(itemRepoMock.save(item2)).thenReturn(item2);
                assertEquals(item2, itemService.addItem(item2));
        }

        @Test
        public void shouldUpdateItem() {
                when(itemRepoMock.save(item1)).thenReturn(item1);
                assertEquals(item1, itemService.updateItem(item1, 1));
        }

}