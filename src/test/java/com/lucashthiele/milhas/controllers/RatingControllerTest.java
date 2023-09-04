package com.lucashthiele.milhas.controllers;

import com.lucashthiele.milhas.domain.rating.Rating;
import com.lucashthiele.milhas.domain.rating.RatingDTO;
import com.lucashthiele.milhas.services.RatingService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class RatingControllerTest {

    @MockBean
    private RatingService ratingService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<RatingDTO> ratingDTOJSON;
    @Autowired
    private JacksonTester<Rating> ratingJSON;

    @Test
    @DisplayName("it should return status 200 when valid data was sent")
    void postRating200() throws Exception {
        var ratingDTO = new RatingDTO("User Name", "Rating Text", "https://lolcahost:8080/image");
        var rating = new Rating(ratingDTO);

        when(ratingService.createRating(any())).thenReturn(rating);

        var response = mvc.perform(
                post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ratingDTOJSON.write(new RatingDTO("User Name", "Rating Text", "https://lolcahost:8080/image"))
                                .getJson())
        ).andReturn().getResponse();

        // Validate HTTP Status Code to be 200
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJSON = ratingJSON.write(
                rating
        ).getJson();

        // Validate JSON sent back by the api
        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    }
}
