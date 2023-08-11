package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {
         public Flux<String> namesFlux(){
              return Flux.fromIterable(List.of("abc","efg","hij"));
         }
         public Flux<String> namesFlux_map(){
             return Flux.fromIterable(List.of("abc","efg","hij")).map(String::toUpperCase);
         }
         public Mono<String> namesMono(){
             return Mono.just("abc");
         }


    public Flux<String> nameFlux_map(){
        return Flux.fromIterable(List.of("mike","harvey","kshitij"))
                .map(String::toUpperCase);
    }

    public Flux<String> nameFlux_mapImmutable(){
        var nameFlux = Flux.fromIterable(List.of("mike","harvey","kshitij"));
        nameFlux.map(String::toUpperCase);
        return nameFlux;
    }

    public Flux<String> nameFlux_filter(int stringLength){
        return Flux.fromIterable(List.of("mike","harvey","kshitij"))
                .filter(s -> s.length()>stringLength)
                .map(s -> s.length()+"-"+s);
    }

    public  Flux<String> nameFlux_filterMap(int stringLength){
        return Flux.fromIterable(List.of("mike","harvey","kshitij"))
                .filter(s -> s.length()>stringLength)
                .flatMap(s-> splitString(s));
    }

    public  Flux<String> nameFlux_filterMap_delay(){
        return Flux.fromIterable(List.of("mike","harvey","kshitij"))

                .flatMap(s-> splitStringWithDelay(s));
    }
    private Flux<String> splitStringWithDelay(String s){
             var charArray = s.split("");
             var time = 200;
             return Flux.fromArray(charArray).delayElements(Duration.ofMillis(time));
    }
    private Flux<String> splitString(String s) {
        var charArray = s.split("");
        return Flux.fromArray(charArray);
    }


    public static void main(String[] args) {

        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

//        service.nameFlux_filterMap(4).subscribe(name -> {
//            System.out.println(name);
//        });
//
//        service.namesMono().subscribe(name -> {
//            System.out.println("Mono name is :" + name);
//        });

        service.nameFlux_filterMap_delay().subscribe(
                name->{
                    System.out.println(name);
                }
        );

        try {
            Thread.sleep(5000); // Wait for 3 seconds (adjust this as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}