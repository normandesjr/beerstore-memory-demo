package com.hibicode.beerstore.controller;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/beers")
public class BeerController {

    @GetMapping("/load")
    public String loadBeers() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();

        List<Beer> beers = new ArrayList<>();
        long usedMemory = 0;
        while (((double) usedMemory / maxMemory) < 0.80) {
            Beer beer = generateBeer(beers.size());
            beers.add(beer);
            usedMemory = runtime.totalMemory();
        }

        return String.format("Carregado %d cervejas! ;)", beers.size());
    }

    private Beer generateBeer(long index) {
        long id = index + 1;
        String name = RandomStringUtils.randomAlphanumeric(15);
        BeerType type = BeerType.values()[new Random().nextInt(3)];
        return new Beer(id, name, type);
    }

}
