package com.burger.services;

import com.burger.models.Currency;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {

    public Currency getCurrency() throws IOException {
        URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=USD,JPY");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();
        System.out.println("status = " + status);

        if(status > 299)
            return null;

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONObject rates = jsonObject.getJSONObject("rates");
        String base = jsonObject.getString("base");
        String date = jsonObject.getString("date");

        Currency c = new Currency();
        Map<String, BigDecimal> ratesMap = new HashMap<>();
        ratesMap.put("USD", BigDecimal.valueOf(rates.getFloat("USD")));
        ratesMap.put("JPY", BigDecimal.valueOf(rates.getFloat("JPY")));

        c.setRates(ratesMap);
        c.setBase(base);
        c.setDate(LocalDate.parse(date));

        return c;
    }
}
