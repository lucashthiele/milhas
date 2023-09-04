package com.lucashthiele.milhas.controllers;

import com.lucashthiele.milhas.domain.rating.Rating;
import com.lucashthiele.milhas.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depoimentos-home")
public class RatingHomeController {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<Rating>> getThreeRatingsRandomized(){
        var ratings = ratingService.getThreeRatingsRandomized();

        return ResponseEntity.ok(ratings);
    }
}
