package com.br.api.wishlist.interfaces.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @GetMapping("/hello")
    public String hello() {
        return "Wishlist API está no ar!";
    }
}
