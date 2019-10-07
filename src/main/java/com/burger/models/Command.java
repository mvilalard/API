package com.burger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Command")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Float total;
    private int done = 0;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date = LocalDate.of(new Date().getYear() + 1900, new Date().getMonth() + 1, new Date().getDate());

    @OneToMany(mappedBy = "command")
    @JsonManagedReference
    List<CommandProduct> products;

    @Transient
    private List<Menu> menuList;

    @Transient
    private List<Product> productList;

    @Transient
    private Currency currency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CommandProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CommandProduct> products) {
        this.products = products;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
