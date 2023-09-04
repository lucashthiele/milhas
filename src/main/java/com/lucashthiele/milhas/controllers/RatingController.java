package com.lucashthiele.milhas.controllers;

import com.lucashthiele.milhas.domain.rating.Rating;
import com.lucashthiele.milhas.domain.rating.RatingDTO;
import com.lucashthiele.milhas.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depoimentos")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody RatingDTO data){
        var rating = ratingService.createRating(data);

        return ResponseEntity.ok(rating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> read(){
        var ratings = ratingService.findAll();

        return ResponseEntity.ok(ratings);
    }

    @PutMapping("{id}")
    public ResponseEntity<Rating> update(@PathVariable Long id, @RequestBody RatingDTO data){
        var rating = ratingService.updateRating(id, data);

        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ratingService.deleteRating(id);

        return ResponseEntity.noContent().build();
    }
}
