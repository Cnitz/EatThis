package com.example.cnitz.eatthis;

/**
 * Created by Cnitz on 10/21/15.
 */
public class Review {

    public int id;
    public String reviewId;
    public String name;
    public double price;
    public String menuItems;
    public String summary;
    public int    rating;
    public String date;

    //Setters
    public Review setId(int id){
        this.id = id;
        return this;
    }
    public Review setReviewId(String reviewId){
        this.reviewId = reviewId;
        return this;
    }
    public Review setName(String name) {
        this.name = name;
        return this;
    }
    public Review setPrice(double price) {
        this.price = price;
        return this;
    }
    public Review setMenuItems(String menuItems) {
        this.menuItems = menuItems;
        return this;
    }
    public Review setSummary(String summary) {
        this.summary = summary;
        return this;
    }
    public Review setRating(int rating) {
        this.rating = rating;
        return this;
    }
    public Review setDate(String date) {
        this.date = date;
        return this;
    }

    //Getters
    public int getId() { return id; }
    public String getReviewId(){
        return reviewId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getMenuItems() {
        return menuItems;
    }
    public String getSummary() {
        return summary;
    }
    public int getRating() {
        return rating;
    }
    public String getDate() {
        return date;
    }

    @Override
    public String toString() { return name + "\n" + date + "\n"; }


}
