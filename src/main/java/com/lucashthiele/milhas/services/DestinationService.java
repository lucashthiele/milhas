package com.lucashthiele.milhas.services;

import com.lucashthiele.milhas.domain.destination.Destination;
import com.lucashthiele.milhas.domain.destination.DestinationDTO;
import com.lucashthiele.milhas.exceptions.DestinationNotFoundException;
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

    public List<Destination> findDestinations(String name) {
        if (name != null) {
            var destinationList = destinationRepository.findByNameContains(name);
            if (destinationList.isEmpty()) throw new DestinationNotFoundException("Nenhum destino encontrado");

            return destinationList;
        }
        else return destinationRepository.findAll();
    }

    public Destination updateDestination(Long id, DestinationDTO data) {
        var destination = destinationRepository.findDestinationById(id);
        destination.setName(data.name());
        destination.setValue(data.value());
        destination.setPictureUrl(data.pictureUrl());

        destinationRepository.save(destination);
        return destination;
    }

    public void deleteDestination(Long id) {
        var destination = destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException("Nenhum destino encontrado"));

        destinationRepository.delete(destination);
    }
}
