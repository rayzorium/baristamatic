package com.raymond.baristamatic.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DrinkIngredientIdTest {
    @Test
    public void equality_is_checked_by_field_values() {
        DrinkIngredientId id1 = new DrinkIngredientId();
        DrinkIngredientId id2 = new DrinkIngredientId();

        id1.setIngredient("Steamed Milk");
        id1.setDrink(12345L);
        id2.setIngredient("Steamed Hams");
        id2.setDrink(12345L);

        assertNotEquals(id1,id2);

        id2.setIngredient("Steamed Milk");

        assertEquals(id1,id2);
    }

    @Test
    public void hashCode_is_generated_by_field_values() {
        DrinkIngredientId id1 = new DrinkIngredientId();
        DrinkIngredientId id2 = new DrinkIngredientId();

        id1.setIngredient("Steamed Milk");
        id1.setDrink(12345L);
        id2.setIngredient("Steamed Hams");
        id2.setDrink(12345L);

        assertNotEquals(id1.hashCode(), id2.hashCode());

        id2.setIngredient("Steamed Milk");

        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
