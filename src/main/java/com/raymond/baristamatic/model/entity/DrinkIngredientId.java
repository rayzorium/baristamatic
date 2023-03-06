package com.raymond.baristamatic.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class DrinkIngredientId implements Serializable {
    private Long drink;
    private String ingredient;

    @Override
    public boolean equals(Object object) {
        if (object instanceof DrinkIngredientId) {
            DrinkIngredientId id = (DrinkIngredientId) object;

            return Objects.equals(drink, id.drink) && Objects.equals(ingredient,id.ingredient);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (drink + ingredient).hashCode();
    }
}
