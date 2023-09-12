package com.lucashthiele.milhas.controllers;

import com.lucashthiele.milhas.domain.destination.Destination;
import com.lucashthiele.milhas.domain.destination.DestinationDTO;
import com.lucashthiele.milhas.domain.rating.RatingDTO;
import com.lucashthiele.milhas.services.DestinationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DestinationControllerTest {

    @MockBean
    private DestinationService destinationService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DestinationDTO> destinationDTOJSON;
    @Autowired
    private JacksonTester<Destination> destinationJSON;

    @Test
    @DisplayName("it should return 200 status code when creating a destination")
    void postDestination200() throws Exception{
        var destinationDTO = new DestinationDTO("Destination Name", new BigDecimal(500), "http://localhost:8080/image");
        var destination = new Destination(destinationDTO);

        when(destinationService.createDestination(any())).thenReturn(destination);

        var response = mvc.perform(
                post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinationDTOJSON.write(new DestinationDTO("Destination Name", new BigDecimal(500), "http://localhost:8080/image"))
                                .getJson())
        ).andReturn().getResponse();

        assertThat200(response.getStatus());

        var expectedJSON = destinationJSON.write(
                destination
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    }

    @Test
    @DisplayName("it should return 200 status code when updating a destination")
    void putDestination200() throws Exception {
        var destinationDTO = new DestinationDTO("Destination Name", new BigDecimal(500), "http://localhost:8080/image");
        var destination = new Destination(destinationDTO);

        when(destinationService.updateDestination(1L, destinationDTO)).thenReturn(destination);

        var response = mvc.perform(
                put("/destinos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(destinationDTOJSON.write(new DestinationDTO("Destination Name", new BigDecimal(500), "http://localhost:8080/image"))
                                .getJson())
        ).andReturn().getResponse();

        assertThat200(response.getStatus());

        var expectedJSON = destinationJSON.write(
                destination
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    }

    private void assertThat200(int statusCode){
        assertThat(statusCode).isEqualTo(HttpStatus.OK.value());
    }
}
