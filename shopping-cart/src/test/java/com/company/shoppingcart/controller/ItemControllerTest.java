package com.company.shoppingcart.controller;
import com.company.shoppingcart.dto.Item;
import com.company.shoppingcart.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest {
    private MockMvc mockMvc;

    @Mock
    ItemService mockItemService;

    @InjectMocks
    ItemController itemController;

    Item item1;
    Item item2;
    Item item3;

    List<Item> itemsList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();

        item1 = new Item();
        item1.setName("perfume");
        item1.setPrice((float) 27.99);
        item1.setCategory("Luxury");
        item1.setImported(true);
        item1.setQuantity(1);

        item2 = new Item();
        item2.setName("perfume");
        item2.setPrice((float) 18.99);
        item2.setCategory("Luxury");
        item2.setImported(false);
        item2.setQuantity(1);

        item3 = new Item();
        item3.setName("headache pills");
        item3.setPrice((float) 9.75);
        item3.setCategory("Medicine");
        item3.setImported(false);
        item3.setQuantity(1);

        itemsList = Arrays.asList(item1, item2, item3);
    }

    @Test
    public void rootContext_ShouldRespondWith404() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void ShouldReturnAllItems() throws Exception {
        when(mockItemService.getAllItems()).thenReturn(itemsList);

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(itemsList.get(0).getName())));

        verify(mockItemService).getAllItems();
    }

    @Test
    public void ShouldAddItem() throws Exception {
        Item item4 = new Item();
        item4.setName("box of chocolates");
        item4.setPrice((float) 11.25);
        item4.setCategory("Food");
        item4.setImported(true);
        item4.setQuantity(1);

        when(mockItemService.addItem(item4)).thenReturn(item4);

        ObjectMapper mapper = new ObjectMapper();
        String objStr = mapper.writeValueAsString(item4);

        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(item4.getName()))).andReturn();

        verify(mockItemService).addItem(item4);
    }

    @Test
    public void ShouldDeleteItem() throws Exception {
        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isOk()).andReturn();

        verify(mockItemService).deleteItem(1);
    }

    @Test
    public void ShouldUpdateItem() throws Exception {
        Item item4 = new Item();
        item4.setName("box of chocolates");
        item4.setPrice((float) 11.25);
        item4.setCategory("Food");
        item4.setImported(true);
        item4.setQuantity(1);

        ObjectMapper mapper = new ObjectMapper();
        String objStr = mapper.writeValueAsString(item4);

        mockMvc.perform(put("/items/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objStr))
                .andExpect(status().isOk()).andReturn();

        verify(mockItemService).updateItem(item4, 4);
    }
}