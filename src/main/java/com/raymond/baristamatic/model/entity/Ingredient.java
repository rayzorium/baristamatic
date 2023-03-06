package com.raymond.baristamatic.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter @Setter
@Entity
public class Ingredient {
    @Transient
    public static final int MAX_AMOUNT = 10;

    @Id
    private String name;
    @JsonIgnore
    private Integer centCost;
    @Column(columnDefinition = "integer default " + MAX_AMOUNT)
    @Min(0) @Max(MAX_AMOUNT)
    private int amount;
}
