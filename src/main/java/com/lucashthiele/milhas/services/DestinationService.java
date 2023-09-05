package com.lucashthiele.milhas.services;

import com.lucashthiele.milhas.domain.destination.Destination;
import com.lucashthiele.milhas.domain.destination.DestinationDTO;
import com.lucashthiele.milhas.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    public Destination createDestination(DestinationDTO data){
        var destination = new Destination(data);
        destinationRepository.save(destination);

        return destination;
    }

    public List<Destination> findDestinationByName(String name) {
        return destinationRepository.findByNameContains(name);
    }
}