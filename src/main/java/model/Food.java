package model;

import java.util.List;

public class Food {
    private int id;
    private String dishName;
    private String ingredients;
    private String price;
    private String deliveryMode;
    private String notes;
    private String category;

    public Food(){}

    public Food(int id, String dishName, String ingredients, String price, String deliveryMode, String notes, String category) {
        this.id = id;
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.price = price;
        this.deliveryMode = deliveryMode;
        this.notes = notes;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", ingredients=" + ingredients +
                ", price='" + price + '\'' +
                ", deliveryMode='" + deliveryMode + '\'' +
                ", notes='" + notes + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
