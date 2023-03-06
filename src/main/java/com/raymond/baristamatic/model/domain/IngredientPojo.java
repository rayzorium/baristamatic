package com.raymond.baristamatic.model.domain;

import com.raymond.baristamatic.model.entity.Ingredient;
import lombok.Getter;

@Getter
public class IngredientPojo {
    private final String name;
    private final int amount;
    public IngredientPojo(Ingredient ingredient) {
        name = ingredient.getName();
        amount = ingredient.getAmount();
    }
}
