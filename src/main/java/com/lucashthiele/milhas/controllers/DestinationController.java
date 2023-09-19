package com.lucashthiele.milhas.controllers;

import com.lucashthiele.milhas.domain.destination.Destination;
import com.lucashthiele.milhas.domain.destination.DestinationDTO;
import com.lucashthiele.milhas.services.DestinationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @PostMapping
    public ResponseEntity<Destination> create(@RequestBody @Valid DestinationDTO data){
        var destination = destinationService.createDestination(data);

        return ResponseEntity.ok(destination);
    }

    @GetMapping
    public ResponseEntity<List<Destination>> read(@RequestParam(value = "nome", required = false) String name){
        return ResponseEntity.ok(destinationService.findDestinations(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> findDestination(@PathVariable Long id){
        return ResponseEntity.ok(destinationService.findDestination(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> update(@PathVariable Long id, @RequestBody DestinationDTO data){
        var destination = destinationService.updateDestination(id, data);

        return ResponseEntity.ok(destination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        destinationService.deleteDestination(id);

        return ResponseEntity.noContent().build();
    }
}
