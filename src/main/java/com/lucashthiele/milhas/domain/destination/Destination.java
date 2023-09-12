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

    public Destination(DestinationDTO data){
        this.name = data.name();
        this.value = data.value();
        this.pictureUrl = data.pictureUrl();
    }
}
