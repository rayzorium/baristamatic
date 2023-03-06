package com.raymond.baristamatic.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@IdClass(DrinkIngredientId.class)
public class DrinkIngredient {
    @Id
    @ManyToOne
    @JoinColumn
    private Drink drink;
    @Id
    @ManyToOne
    private Ingredient ingredient;
    private Integer requiredIngredientAmount;
}
