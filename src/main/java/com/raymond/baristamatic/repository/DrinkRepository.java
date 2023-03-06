package com.raymond.baristamatic.repository;

import com.raymond.baristamatic.model.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink,Long> {
    List<Drink> findByName(String name);
}
