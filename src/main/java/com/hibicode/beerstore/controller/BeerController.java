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

    // Make no sense, the goal is to show some memory problem
    private static final List<Beer> stupidCache = new ArrayList<>();

    @GetMapping
    public List<Beer> listAll() {
        Beer beer = generateBeer();
        stupidCache.add(beer);
        return stupidCache;
    }

    private Beer generateBeer() {
        long id = stupidCache.size() + 1;
        String name = RandomStringUtils.randomAlphanumeric(10);
        BeerType type = BeerType.values()[new Random().nextInt(3)];
        return new Beer(id, name, type);
    }

}
