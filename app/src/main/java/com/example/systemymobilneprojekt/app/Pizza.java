package com.example.systemymobilneprojekt.app;

import java.math.BigDecimal;
import java.util.UUID;


public class Pizza {
    private UUID id;
    private String name;
    private boolean inBasket;
    private String description;
    private BigDecimal price;
    private Category dipCategory;
    private int pizzaId;

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Pizza(){
        id = UUID.randomUUID();
        dipCategory = Category.POMIDOROWY;
    }

    public Category getDipCategory() {
        return dipCategory;
    }


    public void setDipCategory(Category dipCategory) {
        this.dipCategory = dipCategory;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInBasket() {
        return inBasket;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInBasket(boolean inBasket) {
        this.inBasket = inBasket;
    }
}
