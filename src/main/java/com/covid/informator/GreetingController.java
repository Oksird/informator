package com.covid.informator;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;



@Controller
public class GreetingController {

    @GetMapping public String covid(@RequestParam(name = "country", required = false, defaultValue = "Ukraine")String name, Map<String, Object> model) throws UnirestException {
        model.put("country",name);
        Data data = new Data(model,name);
        model.put("total",data.getTotal());
        model.put("recovered",data.getRecovered());
        model.put("death",data.getDeath());
        return "covid";
    }

}