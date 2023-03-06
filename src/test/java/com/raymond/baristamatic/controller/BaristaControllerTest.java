package com.raymond.baristamatic.controller;

import com.raymond.baristamatic.exception.DrinkNotFoundException;
import com.raymond.baristamatic.exception.InsufficientIngredientException;
import com.raymond.baristamatic.model.domain.IngredientPojo;
import com.raymond.baristamatic.model.entity.Ingredient;
import com.raymond.baristamatic.service.IngredientStockManager;
import com.raymond.baristamatic.service.Menu;
import com.raymond.baristamatic.service.OrderDrink;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(BaristaController.class)
public class BaristaControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    Menu menu;
    @MockBean
    OrderDrink orderDrink;
    @MockBean
    IngredientStockManager ingredientStockManager;

    @Test
    public void orderDrink_returns_valid_json() throws Exception {
        Mockito.when(orderDrink.orderDrink(any())).thenReturn(1);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orderDrink")
                .queryParam("drink","Coffee")).andReturn();


        assertEquals("{\"message\":\"Enjoy your Coffee!\"}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void restock_returns_valid_json() throws Exception {
        Mockito.when(orderDrink.orderDrink(any())).thenReturn(1);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/restock")).andReturn();

        assertEquals("{\"message\":\"Restock complete!\"}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void ingredientInventory_returns_valid_json() throws Exception {
        IngredientPojo ingredientPojo = new IngredientPojo(new Ingredient() {{
            this.setName("Steamed Milk");
            this.setAmount(5);
        }});
        Mockito.when(ingredientStockManager.checkInventory()).thenReturn(List.of(ingredientPojo));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/checkStock")).andReturn();

        assertEquals("[{\"name\":\"Steamed Milk\",\"amount\":5}]",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void orderDrink_returns_proper_error_message_when_drink_not_found() throws Exception {
        Mockito.when(orderDrink.orderDrink(anyString())).thenThrow(new DrinkNotFoundException("Coffeee"));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orderDrink")
                .queryParam("drink","Coffeee")).andReturn();

        assertEquals("{\"message\":\"Drink not found. Please see /menu\"}",mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void orderDrink_returns_proper_error_message_when_insufficient_ingredients() throws Exception {
        Mockito.when(orderDrink.orderDrink(anyString())).thenThrow(new InsufficientIngredientException("Steamed Milk"));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/orderDrink")
                .queryParam("drink","Coffee")).andReturn();

        assertEquals("{\"message\":\"Insufficient ingredients: Steamed Milk. Please /restock\"}",mvcResult.getResponse().getContentAsString());
    }
}
