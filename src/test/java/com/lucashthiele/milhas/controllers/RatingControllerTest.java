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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    @Autowired
    private JacksonTester<List<Rating>> ratingListJSON;

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
        assertThat200(response.getStatus());

        var expectedJSON = ratingJSON.write(
                rating
        ).getJson();

        // Validate JSON sent back by the api
        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    }

    @Test
    @DisplayName("it should return an array of Ratings")
    void getRatings200() throws Exception {
        var ratingDTO = new RatingDTO("User Name","Rating Text", "https://picture-ulr.com");
        var rating = new Rating(ratingDTO);
        var mockRatingList = new ArrayList<Rating>();
        mockRatingList.add(rating);

        when(ratingService.findAll()).thenReturn(mockRatingList);

        var response = mvc.perform(get("/depoimentos")).andReturn().getResponse();

        assertThat200(response.getStatus());

        var expectedJSON = ratingListJSON.write(
                mockRatingList
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    }

    private void assertThat200(int statusCode){
        assertThat(statusCode).isEqualTo(HttpStatus.OK.value());
    }
}
