package com.burger.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Currency {

    private Map<String, BigDecimal> rates;
    private String base;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
