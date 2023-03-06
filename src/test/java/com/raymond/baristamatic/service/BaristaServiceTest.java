package com.raymond.baristamatic.service;

import com.raymond.baristamatic.exception.DrinkNotFoundException;
import com.raymond.baristamatic.exception.InsufficientIngredientException;
import com.raymond.baristamatic.model.domain.IngredientPojo;
import com.raymond.baristamatic.model.domain.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //Starting application and hitting database for real. I know it's not really a unit test, but it's too time consuming to build mocks.
public class BaristaServiceTest {
    @Autowired
    BaristaService baristaService;

    @Test
    @Sql(statements = "update ingredient set amount = 10")
    public void getMenu_returns_menuItems() {
        List<MenuItem> list = baristaService.getMenu();

        assertEquals(6, list.size());
    }

    @Test
    @Sql(statements = "update ingredient set amount = 0")
    public void inventory_functions_all_work() {
        List<IngredientPojo> list = baristaService.checkInventory();
        assertEquals(0,list.get(0).getAmount());

        baristaService.refillIngredients();
        list = baristaService.checkInventory();
        assertEquals(10,list.get(0).getAmount());
    }

    @Test
    @Sql(statements = "update ingredient set amount = 10")
    public void successful_orderDrink_changes_ingredient_amounts() throws DrinkNotFoundException, InsufficientIngredientException {
        baristaService.orderDrink("Coffee");

        List<IngredientPojo> list = baristaService.checkInventory();

        for (IngredientPojo ip : list) {
            if (ip.getName().equals("Coffee")) assertEquals(7,ip.getAmount());
            else if (ip.getName().equals("Sugar")) assertEquals(9,ip.getAmount());
            else if (ip.getName().equals("Cream")) assertEquals(9,ip.getAmount());
        }
    }

    @Test
    @Sql(statements = "update ingredient set amount = 0")
    public void orderDrink_throws_exception_when_insufficient_ingredients() {
        InsufficientIngredientException e = assertThrows(InsufficientIngredientException.class, () -> {
            baristaService.orderDrink("Coffee");
        });

        assertTrue(e.getMessage().contains("Coffee"));
        assertTrue(e.getMessage().contains("Cream"));
        assertTrue(e.getMessage().contains("Sugar"));
    }

    @Test
    @Sql(statements = "update ingredient set amount = 10")
    public void orderDrink_throws_exception_when_invalid_drink() {
        DrinkNotFoundException e = assertThrows(DrinkNotFoundException.class, () -> {
            baristaService.orderDrink("Sprite");
        });
    }
}
