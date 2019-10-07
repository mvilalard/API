package com.burger;

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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PromotionControllerTest {
    @Autowired
    private MockMvc mvc;

    private Promotion getPromotion() {
        Promotion myTestPromotion = new Promotion();
        myTestPromotion.setDescription("Promotion test");
        myTestPromotion.setPrerequisite("Prequisite");
        myTestPromotion.setAvailable(1);
        myTestPromotion.setStart_date(LocalDate.now());
        myTestPromotion.setEnd_date(LocalDate.now().plusDays(1)); //.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return myTestPromotion;
    }

    @Test
    public void should_get_promotions() throws Exception {
        this.mvc.perform(get("/promotion/")).andExpect(status().isOk());
    }


    @Test
    public void should_get_promotion_by_id() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/promotion/")).andReturn();

        Type listType = new TypeToken<ArrayList<Promotion>>(){}.getType();
        List<Promotion> promotions = gson.fromJson(result.getResponse().getContentAsString(), listType);

        this.mvc.perform(get("/promotion/"+promotions.get(0).getId())).andExpect(status().isOk());

    }

    @Test
    public void should_add() throws Exception {
        Gson gson = new Gson();
        this.mvc.perform(
                post("/promotion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(getPromotion())))
                .andExpect(status().isOk());
    }


    @Test
    public void should_update_promotion() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/promotion/")).andReturn();

        Type listType = new TypeToken<ArrayList<Promotion>>(){}.getType();
        List<Promotion> promotions = gson.fromJson(result.getResponse().getContentAsString(), listType);
        Promotion toUpdate = promotions.get(0);
        toUpdate.setDescription("Updated promotion");

        this.mvc.perform(put("/promotion/"+toUpdate.getId())).andExpect(status().isOk());
    }

    @Test
    public void should_delete_promotion_by_id() throws Exception {
        Gson gson = new Gson();
        MvcResult result = this.mvc.perform(get("/promotion/")).andReturn();

        Type listType = new TypeToken<ArrayList<Promotion>>(){}.getType();
        List<Promotion> promotions = gson.fromJson(result.getResponse().getContentAsString(), listType);

        this.mvc.perform(MockMvcRequestBuilders
                .delete("/promotion/"+promotions.get(0).getId()))
                .andExpect(status().isOk());
    }
}
