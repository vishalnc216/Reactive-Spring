package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

    @Test
    void nameFlux() {

      var nameFlux =   service.namesFlux();

        StepVerifier.create(nameFlux)
                .expectNext("mike","harvey","Kshitij")
                .verifyComplete();
    }

    @Test
    void nameFlux_map() {
        var nameFlux = service.nameFlux_map();

        StepVerifier.create(nameFlux)
                .expectNext("MIKE","HARVEY","KSHITIJ")
                .verifyComplete();
    }

    @Test
    void nameFlux_mapImmutable() {
        var nameFlux = service.nameFlux_mapImmutable();

        StepVerifier.create(nameFlux)
                .expectNext("mike","harvey","kshitij")
                .verifyComplete();
    }

    @Test
    void nameFlux_filter() {

        int stringLength = 4;

        var nameFlux = service.nameFlux_filter(stringLength);

        StepVerifier.create(nameFlux)
                .expectNext("harvey","kshitij")
                .verifyComplete();


    }

    @Test
    void nameFlux_filterMap() {
        int stringLength = 4;

        var nameFlux = service.nameFlux_filterMap(stringLength);

        StepVerifier.create(nameFlux)
                .expectNext("h","a","r","v","e","y","k","s","h","i","t","i","j")
                .verifyComplete();


    }

    @Test
    void nameFlux_filterMap_delay() {
        var nameFlux = service.nameFlux_filterMap_delay();

        StepVerifier.create(nameFlux)
                .expectNext("m","i","k","e","h","a","r","v","e","y","k","s","h","i","t","i","j")
                .verifyComplete();


    }
}