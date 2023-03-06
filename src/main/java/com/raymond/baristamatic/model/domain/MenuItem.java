package com.raymond.baristamatic.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raymond.baristamatic.model.entity.Drink;
import com.raymond.baristamatic.model.entity.DrinkIngredient;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MenuItem {
    private final long id;
    private final String name;
    @JsonProperty("price")
    private int centCost;
    private int amountAvailable;
    public MenuItem(Drink drinkEntity) {
        id = drinkEntity.getId();
        name = drinkEntity.getName();

        amountAvailable = Integer.MAX_VALUE;
        for (DrinkIngredient drink : drinkEntity.getDrinkIngredients()) {
            int reqIngredientAmt = drink.getRequiredIngredientAmount();
            centCost += reqIngredientAmt * drink.getIngredient().getCentCost();
            amountAvailable = Math.min(amountAvailable,drink.getIngredient().getAmount() / reqIngredientAmt);
        }
    }

    @JsonProperty("price")
    public String getPrice() {
        BigDecimal bd = new BigDecimal(centCost);
        return '$' + bd.movePointLeft(2).toString();
    }
}
