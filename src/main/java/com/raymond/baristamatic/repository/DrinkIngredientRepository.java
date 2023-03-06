package com.raymond.baristamatic.repository;

import com.raymond.baristamatic.model.entity.DrinkIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkIngredientRepository extends JpaRepository<DrinkIngredient,Long> {
    List<DrinkIngredient> findByDrinkId(Long id);
}
