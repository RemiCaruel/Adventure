package com.adventurer.logic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import java.util.stream.Stream;

@Component
public class AdventureRunner implements CommandLineRunner {

    private int id;

    public AdventureRunner() {
        this.id = 0;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Top beers from https://www.beeradvocate.com/lists/top/
        System.out.println(strings);
    }
}