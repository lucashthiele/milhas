package com.lucashthiele.milhas.repositories;

import com.lucashthiele.milhas.domain.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findRatingById(Long id);

    @Query("""
            select r
              from Rating r
             order by rand()
             limit 3
            """)
    List<Rating> findThreeRatingsRandomized();
}
