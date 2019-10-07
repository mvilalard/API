package com.burger;

import com.burger.models.Command;
import com.burger.models.Menu;
import com.burger.models.Product;
import com.burger.models.Promotion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommandControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void should_get_products() throws Exception {
        this.mvc.perform(get("/command/")).andExpect(status().isOk());
    }



    private Command getMenu() throws Exception {

        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/promotion/")).andReturn();

        Type listType = new TypeToken<ArrayList<Promotion>>(){}.getType();
        List<Promotion> promotions = gson.fromJson(result.getResponse().getContentAsString(), listType);
        System.out.println("promotions");
        System.out.println(result.getResponse().getContentAsString());

        MvcResult resultProducts = this.mvc.perform(get("/product/")).andReturn();

        Type listTypeProducts = new TypeToken<ArrayList<Product>>(){}.getType();
        List<Product> products = gson.fromJson(resultProducts.getResponse().getContentAsString(), listTypeProducts);
        System.out.println("products");
        System.out.println(products);

        List<Product> menuProducts = products.subList(0, 2);
        System.out.println("menuProducts");
        System.out.println(menuProducts);

        Command myTestCommand = new Command();
        myTestCommand.setDone(0);
        myTestCommand.setProductList(menuProducts);
        myTestCommand.setDate(LocalDate.now());
        myTestCommand.setTotal(18.42f);
        return myTestCommand;
    }

    @Test
    public void should_get_menus() throws Exception {
        this.mvc.perform(get("/menu/")).andExpect(status().isOk());
    }

    @Test
    public void should_get_menus_by_id() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/menu/")).andReturn();

        Type listType = new TypeToken<ArrayList<Menu>>(){}.getType();
        List<Menu> menus = gson.fromJson(result.getResponse().getContentAsString(), listType);

        this.mvc.perform(get("/menu/"+menus.get(0).getId())).andExpect(status().isOk());
    }

    @Test
    public void should_add() throws Exception {
        Gson gson = new Gson();
        this.mvc.perform(
                post("/menu/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(getMenu())))
                .andExpect(status().isOk());
    }


    @Test
    public void should_update_menu() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/menu/")).andReturn();

        Type listType = new TypeToken<ArrayList<Menu>>(){}.getType();
        List<Menu> menus = gson.fromJson(result.getResponse().getContentAsString(), listType);
        Menu toUpdate = menus.get(0);
        toUpdate.setDescription("Updated menu");

        this.mvc.perform(put("/menu/"+toUpdate.getId())).andExpect(status().isOk());
    }

    @Test
    public void should_delete_menu_by_id() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/menu/")).andReturn();

        Type listType = new TypeToken<ArrayList<Menu>>(){}.getType();
        List<Menu> menus = gson.fromJson(result.getResponse().getContentAsString(), listType);

        this.mvc.perform(MockMvcRequestBuilders
                .delete("/promotion/"+menus.get(0).getId()))
                .andExpect(status().isOk());
    }
}
