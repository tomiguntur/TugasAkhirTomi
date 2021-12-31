package com.tomiguntur.ProjectAkhir.Shoes.Model;

import com.google.firebase.firestore.DocumentId;

public class ShoesModel {

    @DocumentId
    String shoesid;
    String description, imageURL, shoesname;
    int price, quantity;

    public ShoesModel() {
    }

    public ShoesModel(String shoesid, String description, String imageURL, String shoesname, int price, int quantity) {
        this.shoesid = shoesid;
        this.description = description;
        this.imageURL = imageURL;
        this.shoesname = shoesname;
        this.price = price;
        this.quantity = quantity;
    }

    public String getShoesid() {
        return shoesid;
    }

    public void setShoesid(String shoesid) {
        this.shoesid = shoesid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoesModel{" +
                "shoesid='" + shoesid + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", shoesname='" + shoesname + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
