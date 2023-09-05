package com.lucashthiele.milhas.repositories;

import com.lucashthiele.milhas.domain.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findDestinationById(Long id);
    List<Destination> findByNameContains(String name);
}
