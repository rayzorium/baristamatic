package com.raymond.baristamatic.service;

import com.raymond.baristamatic.model.domain.IngredientPojo;
import com.raymond.baristamatic.model.entity.Ingredient;

import java.util.List;

public interface IngredientStockManager {
    public int changeIngredientStock(List<Ingredient> ingredients, List<Integer> newStockValues);
    public List<IngredientPojo> checkInventory();
    public int refillIngredients();
}
