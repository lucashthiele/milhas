package com.lucashthiele.milhas.services;

import com.lucashthiele.milhas.domain.rating.Rating;
import com.lucashthiele.milhas.domain.rating.RatingDTO;
import com.lucashthiele.milhas.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating createRating(RatingDTO data){
        var rating = new Rating(data);
        ratingRepository.save(rating);

        return rating;
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating updateRating(Long id, RatingDTO data) {
        var rating = ratingRepository.findRatingById(id);
        rating.setName(data.name());
        rating.setText(data.rating());
        rating.setPictureUrl(data.pictureUrl());

        ratingRepository.save(rating);

        return rating;
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public List<Rating> getThreeRatingsRandomized() {
        return ratingRepository.findThreeRatingsRandomized();
    }
}
