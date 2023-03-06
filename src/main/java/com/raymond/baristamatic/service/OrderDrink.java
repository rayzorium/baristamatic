package com.raymond.baristamatic.service;

import com.raymond.baristamatic.exception.DrinkNotFoundException;
import com.raymond.baristamatic.exception.InsufficientIngredientException;

public interface OrderDrink {
    int orderDrink(String drinkName) throws DrinkNotFoundException, InsufficientIngredientException;
}
