package com.burger.models;

import com.burger.Embeddable.ListProductKey;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "ListProduct")
public class ListProduct {

    @EmbeddedId
    ListProductKey id;

    @ManyToOne
    @MapsId("menu_id")
    @JsonBackReference(value = "menu_list")
    @JoinColumn(name = "menu_id")
    Menu menu;

    @ManyToOne
    @MapsId("product_id")
    @JsonBackReference(value = "product_list")
    @JoinColumn(name = "product_id")
    Product product;

    float price;
    int position;

    public ListProductKey getId() {
        return id;
    }

    public void setId(ListProductKey id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
