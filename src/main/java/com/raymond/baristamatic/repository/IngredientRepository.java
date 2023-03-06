package com.raymond.baristamatic.repository;

import com.raymond.baristamatic.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,String> {
}
