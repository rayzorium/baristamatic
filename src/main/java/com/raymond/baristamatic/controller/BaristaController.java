package com.raymond.baristamatic.controller;

import com.raymond.baristamatic.model.domain.IngredientPojo;
import com.raymond.baristamatic.model.domain.MenuItem;
import com.raymond.baristamatic.exception.DrinkNotFoundException;
import com.raymond.baristamatic.exception.InsufficientIngredientException;
import com.raymond.baristamatic.service.IngredientStockManager;
import com.raymond.baristamatic.service.Menu;
import com.raymond.baristamatic.service.OrderDrink;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class BaristaController {
    IngredientStockManager ingredientStockManager;
    OrderDrink orderDrink;
    Menu menu;

    @GetMapping("/menu")
    public List<MenuItem> menu() {
        return menu.getMenu();
    }

    @GetMapping("/checkStock")
    public List<IngredientPojo> checkStock() {
        return ingredientStockManager.checkInventory();
    }

    @PostMapping("/restock")
    public Map<String,String> restock() {
        ingredientStockManager.refillIngredients();

        Map<String,String> response = new HashMap<>();
        response.put("message","Restock complete!");

        return response;
    }

    @PostMapping("/orderDrink")
    public Map<String,String> orderDrink(@RequestParam String drink) throws DrinkNotFoundException, InsufficientIngredientException {
        orderDrink.orderDrink(drink);

        Map<String,String> response = new HashMap<>();
        response.put("message","Enjoy your " + drink + '!');

        return response;
    }
}
