package com.lucashthiele.milhas.domain.destination;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "destination")
@Entity(name = "Destination")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal value;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column(name = "secondary_picture_url")
    private String secondaryPictureUrl;
    @Column(name = "meta_text")
    private String metaText;
    private String description;

    public Destination(DestinationDTO data){
        this.name = data.name();
        this.value = data.value();
        this.pictureUrl = data.pictureUrl();
        this.secondaryPictureUrl = data.secondaryPictureUrl();
        this.metaText = data.metaText();
        this.description = data.description();
    }
}
