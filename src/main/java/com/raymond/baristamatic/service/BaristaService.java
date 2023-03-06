package com.raymond.baristamatic.service;

import com.raymond.baristamatic.model.domain.IngredientPojo;
import com.raymond.baristamatic.model.domain.MenuItem;
import com.raymond.baristamatic.model.entity.Drink;
import com.raymond.baristamatic.model.entity.DrinkIngredient;
import com.raymond.baristamatic.model.entity.Ingredient;
import com.raymond.baristamatic.exception.DrinkNotFoundException;
import com.raymond.baristamatic.exception.InsufficientIngredientException;
import com.raymond.baristamatic.repository.DrinkIngredientRepository;
import com.raymond.baristamatic.repository.DrinkRepository;
import com.raymond.baristamatic.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class BaristaService implements Menu, OrderDrink, IngredientStockManager {

    private final int DEFAULT_INGREDIENT_STOCK_FILL_AMOUNT = Ingredient.MAX_AMOUNT;
    private DrinkRepository drinkRepository;
    private DrinkIngredientRepository drinkIngredientRepository;
    private IngredientRepository ingredientRepository;

    @Override
    public List<MenuItem> getMenu() {
        return drinkRepository.findAll().stream().map(MenuItem::new).collect(Collectors.toList());
    }

    @Override
    public int orderDrink(String drinkName) throws DrinkNotFoundException, InsufficientIngredientException {
        List<Drink> drinkEntities = drinkRepository.findByName(drinkName);
        if (drinkEntities.size() == 0) {
            throw new DrinkNotFoundException(drinkName);
        }

        List<DrinkIngredient> drinks = drinkIngredientRepository.findByDrinkId(drinkEntities.get(0).getId());

        StringJoiner missingIngredients = new StringJoiner(", ");
        List<Ingredient> ingredientsToSave = new ArrayList<>();
        List<Integer> newStockValues = new ArrayList<>();
        for (DrinkIngredient drink : drinks) {
            Ingredient ingredient = drink.getIngredient();
            int amt = ingredient.getAmount();
            int amtNeeded = drink.getRequiredIngredientAmount();

            if (amtNeeded > amt) {
                missingIngredients.add(ingredient.getName());
            } else {
                newStockValues.add(amt - amtNeeded);
                ingredientsToSave.add(ingredient);
            }
        }

        if (missingIngredients.length() > 0) {
            throw new InsufficientIngredientException(missingIngredients.toString());
        }

        changeIngredientStock(ingredientsToSave,newStockValues);

        return 1;
    }

    @Override
    public int changeIngredientStock(List<Ingredient> ingredients, List<Integer> newStockValues) {
        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.get(i).setAmount(newStockValues.get(i));
        }

        ingredientRepository.saveAll(ingredients);

        return 1;
    }

    @Override
    public List<IngredientPojo> checkInventory() {
        return ingredientRepository.findAll().stream().map(IngredientPojo::new).collect(Collectors.toList());
    }

    @Override
    public int refillIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        return changeIngredientStock(ingredients,
                Collections.nCopies(ingredients.size(),DEFAULT_INGREDIENT_STOCK_FILL_AMOUNT));
    }
}
