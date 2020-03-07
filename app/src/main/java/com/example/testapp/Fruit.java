package com.example.testapp;

/**
 * Define fruit class which represents the data model being displayed by the RecyclerView
 */

public class Fruit {

    private String type;

    private int price;

    private int weight;

    public Fruit(String type, Integer price, Integer weight){
        this.type = type;
        this.price = price;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



    @Override
    public String toString() {
        return this.type + " " + this.price + " " + this.weight;
    }
}
