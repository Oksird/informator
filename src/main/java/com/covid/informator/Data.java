package com.covid.informator;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Map;

public class Data {
    private Integer total;
    private Integer death;
    private Integer recovered;

    public Integer getTotal() {
        return total;
    }

    public Integer getDeath() {
        return death;
    }

    public Integer getRecovered() {
        return recovered;
    }

    Data(Map<String,Object> model,String name) throws UnirestException {
        HttpResponse<JsonNode> responseData = Unirest.get("https://covid-193.p.rapidapi.com/statistics?country="+name)
                .header("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .header("x-rapidapi-key", "4717e9687emsh52ff0b8697a811fp1b7b6cjsn0880fa04d1be")
                .asJson();

        String json = responseData.getBody().toString();

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        total =  JsonPath.read(document,"$.response[0].cases.total");
        recovered = JsonPath.read(document,"$.response[0].cases.recovered");
        death = JsonPath.read(document,"$.response[0].deaths.total");
    }
}
