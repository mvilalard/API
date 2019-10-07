package com.burger;

import com.burger.models.Product;
import com.burger.models.Promotion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    private Product getProduct() {
        Product myTestProduct = new Product();
        myTestProduct.setId(1842);
        myTestProduct.setName("Mon produit de test");
        myTestProduct.setPrice(5.42f);
        myTestProduct.setAvailable(1);
        myTestProduct.setCategory(1);
        myTestProduct.setHighlight(0);
        return myTestProduct;
    }

    @Test
    public void should_add() throws Exception {
        Gson gson = new Gson();
        this.mvc.perform(
                post("/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(getProduct())))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_products() throws Exception {
        this.mvc.perform(get("/product/")).andExpect(status().isOk());
    }

    @Test
    public void should_update() throws Exception {
        Gson gson = new Gson();
        String productName = "test.product.update.mivi";
        Product product = getProduct();
        product.setName(productName);
        MvcResult result = this.mvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(product)))
                .andReturn();


        Product toPut = gson.fromJson(result.getResponse().getContentAsString(), Product.class);

        toPut.setPrice(1842.69f);

        this.mvc.perform(
                put("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(toPut)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_product_by_id() throws Exception {
        Gson gson = new Gson();
        String productName = "test.product.get.by.id.mivi";
        Product product = getProduct();
        product.setName(productName);
        MvcResult result = this.mvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(product)))
                .andReturn();
        Product toGet = gson.fromJson(result.getResponse().getContentAsString(), Product.class);

        this.mvc.perform(get("/product/"+toGet.getId())).andExpect(status().isOk());

        this.mvc.perform(MockMvcRequestBuilders.delete("/product/"+toGet.getId())).andExpect(status().isOk());
    }

    @Test
    public void should_delete_test_product() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/product/")).andReturn();

        Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
        List<Product> products = gson.fromJson(result.getResponse().getContentAsString(), listType);

        for (Product current: products) {
            if(current.getName().equals(getProduct().getName())) {
                this.mvc.perform(MockMvcRequestBuilders
                        .delete("/product/"+current.getId()))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    public void should_delete_fail_because_wrong_id() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/product/")).andReturn();

        Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
        List<Product> products = gson.fromJson(result.getResponse().getContentAsString(), listType);
        System.out.println(products);

        for (Product current: products) {
            if(current.getName().equals(getProduct().getName())) {
                this.mvc.perform(MockMvcRequestBuilders
                        .delete("/product/"+"-1"))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    public void should_fail_add_product_because_no_price() throws Exception {
        Product myTestProduct = new Product();
        myTestProduct.setName("No price product");
        myTestProduct.setAvailable(1);
        myTestProduct.setCategory(1);
        myTestProduct.setHighlight(0);
        myTestProduct.setPrice(null);

        Gson gson = new Gson();
        this.mvc.perform(
                post("/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(myTestProduct)))
            .andExpect(status().isUnprocessableEntity());
    }

    public void should_fail_add_product_because_no_category() throws Exception {
        Product myTestProduct = new Product();
        myTestProduct.setName("No price product");
        myTestProduct.setAvailable(1);
        myTestProduct.setCategory(null);
        myTestProduct.setHighlight(0);
        myTestProduct.setPrice(5.0f);

        Gson gson = new Gson();
        this.mvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(myTestProduct)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_fail_add_product_because_no_name() throws Exception {
        Product myTestProduct = new Product();
        myTestProduct.setName(null);
        myTestProduct.setAvailable(1);
        myTestProduct.setCategory(1);
        myTestProduct.setHighlight(0);
        myTestProduct.setPrice(5.0f);

        Gson gson = new Gson();
        this.mvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(myTestProduct)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_fail_add_product_because_content_type() throws Exception {
        Gson gson = new Gson();
        this.mvc.perform(
                post("/product/")
                        .content(gson.toJson(getProduct())))
                .andExpect(status().isUnsupportedMediaType());
    }


    @Test
    public void should_get_product_by_promotion_id() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/promotion/")).andReturn();

        Type listType = new TypeToken<ArrayList<Promotion>>(){}.getType();
        List<Promotion> promotions = gson.fromJson(result.getResponse().getContentAsString(), listType);
        System.out.println(promotions);

        Promotion productPromotion = null;
        if(promotions != null && !promotions.isEmpty()) {
            productPromotion = promotions.get(0);
        }

        Product promotedProduct = getProduct();
        promotedProduct.setPromotion(productPromotion);
        promotedProduct.setName("mivi.test.promoted");

        this.mvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(promotedProduct)))
                .andExpect(status().isOk());


        MvcResult result2 = this.mvc.perform(
                get("/product/promotion/"+productPromotion.getId())
                .content(gson.toJson(promotedProduct)))
                .andExpect(status().isOk())
                .andReturn();

        Product promotedReturned = gson.fromJson(result2.getResponse().getContentAsString(), Product.class);

        Assert.assertEquals(promotedProduct, promotedReturned);

    }
}
