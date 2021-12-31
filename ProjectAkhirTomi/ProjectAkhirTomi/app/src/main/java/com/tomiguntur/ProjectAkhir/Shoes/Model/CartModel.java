package com.tomiguntur.ProjectAkhir.Shoes.Model;

public class CartModel {

    String shoesname, imageURL;
    int quantity, totalprice;

    public CartModel() {
    }

    public CartModel(String shoesname, String imageURL, int quantity, int totalprice) {
        this.shoesname = shoesname;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
