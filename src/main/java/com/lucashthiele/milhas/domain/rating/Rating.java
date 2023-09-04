package com.lucashthiele.milhas.domain.rating;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "rating")
@Entity(name = "Rating")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String text;
    @Column(name = "picture_url")
    private String pictureUrl;

    public Rating(RatingDTO data) {
        this.name = data.name();
        this.text = data.rating();
        this.pictureUrl = data.pictureUrl();
    }
}
