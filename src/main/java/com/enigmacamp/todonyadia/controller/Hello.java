package com.enigmacamp.todonyadia.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Hello {

    // http:localhost:8080/hello
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String getHello(){
//    return "Hello yall";
//    }

    // spesific get
//    @GetMapping("/")
//    public String getAnotherHello(){ return "Hello with getMapping!"; }
//
//    path variable
//    @GetMapping("/products/{id}")
//    public  String pathVar(@PathVariable String id){ return "Product id: " + id; }

    // req params
//    @GetMapping("/products")
//    public String reqParams(@RequestParam(name = "page") String page){
//        return "Get result params: " + page;
//    }

    // req params with 2 params and default value
//    @GetMapping("/products")
//    public String reqParamsWithLimit(
//        @RequestParam(name = "page", defaultValue = "1") int page,
//        @RequestParam(name = "size", defaultValue = "10") int size
//    ){
//        return "Page: " + page + ", size:" + size;
//    }

//    @PostMapping("/products")
//    public String reqBody(@RequestBody HashMap<String, String> map){
//        return "Result: " + map;
//    }
}
