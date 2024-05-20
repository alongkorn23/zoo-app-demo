package demo.zooapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.api.dto.AnimalResponse;
import demo.zooapp.api.dto.FeedAnimalRequest;
import demo.zooapp.api.dto.SearchAnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.exception.AnimalNotFoundException;
import demo.zooapp.helper.TestHelper;
import demo.zooapp.model.SearchCriteria;
import demo.zooapp.service.ZooManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

@WebMvcTest(ZooManagementController.class)
class ZooManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZooManagementService zooManagementService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAnimals_returns_ok_status() throws Exception {
        Animal animal = TestHelper.createAnimal();
        Mockito.when(zooManagementService.getAnimals()).thenReturn(List.of(animal));
        mockMvc.perform(get("/zoo/animals")).andExpect(status().isOk());
    }

    @Test
    void createAnimal_returns_ok_status() throws Exception {
        Animal animal = TestHelper.createAnimal();
        AnimalRequest animalRequest = TestHelper.createAnimalRequest();
        Mockito.when(zooManagementService.createAnimal(any())).thenReturn(animal);
        mockMvc.perform(post("/zoo/animal").
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(animalRequest))).
                andExpect(status().isOk()).
                andExpect(content().json(objectMapper.writeValueAsString(animal)));
    }

    @Test
    void createAnimal_returns_bad_request_status_when_request_field_name_invalid() throws Exception {
        Animal animal = TestHelper.createAnimal();
        AnimalRequest animalRequest = new AnimalRequest(
                "", // name is empty
                10,
                5,
                "white",
                "male",
                "dog");
        Mockito.when(zooManagementService.createAnimal(any())).thenReturn(animal);
        mockMvc.perform(post("/zoo/animal").
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(animalRequest))).
                andExpect(status().isBadRequest());
    }

    @Test
    void createAnimal_returns_bad_request_status_when_request_field_weight_invalid() throws Exception {
        Animal animal = TestHelper.createAnimal();
        AnimalRequest animalRequest = new AnimalRequest(
                "Lulu",
                0, // Invalid value
                5,
                "white",
                "male",
                "dog");
        Mockito.when(zooManagementService.createAnimal(any())).thenReturn(animal);
        mockMvc.perform(post("/zoo/animal").
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(animalRequest))).
                andExpect(status().isBadRequest());
    }

    @Test
    void feedAnimal_returns_ok_status() throws Exception {
        UUID id = TestHelper.TEST_UUID;
        Animal animal = TestHelper.createAnimal();
        FeedAnimalRequest feedAnimalRequest = new FeedAnimalRequest(10);
        Mockito.when(zooManagementService.feedAnimal(id, feedAnimalRequest.foodWeight())).thenReturn(animal);
        mockMvc.perform(put("/zoo/feed/animal/{id}", id).
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(feedAnimalRequest))).
                andExpect(status().isOk());
    }

    @Test
    void feedAnimal_returns_bad_request_status_when_request_field_foodWeight_invalid() throws Exception {
        UUID id = TestHelper.TEST_UUID;
        Animal animal = TestHelper.createAnimal();
        FeedAnimalRequest feedAnimalRequest = new FeedAnimalRequest(0); // invalid value
        Mockito.when(zooManagementService.feedAnimal(id, feedAnimalRequest.foodWeight())).thenReturn(animal);
        mockMvc.perform(put("/zoo/feed/animal/{id}", id).
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(feedAnimalRequest))).
                andExpect(status().isBadRequest());
    }


    @Test
    void feedAnimal_returns_not_found_status_when_animal_not_exists() throws Exception {
        UUID id = TestHelper.TEST_UUID;
        FeedAnimalRequest feedAnimalRequest = new FeedAnimalRequest(10);
        Mockito.when(zooManagementService.feedAnimal(id, feedAnimalRequest.foodWeight())).thenThrow(new AnimalNotFoundException("Animal not found"));
        mockMvc.perform(put("/zoo/feed/animal/{id}", id).
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(feedAnimalRequest))).
                andExpect(status().isNotFound());

    }

    @Test
    void deleteAnimal_returns_no_content_status() throws Exception {
        UUID id = TestHelper.TEST_UUID;
        Mockito.doNothing().when(zooManagementService).deleteAnimalById(id);
        mockMvc.perform(delete("/zoo/animal/{id}", id)).andExpect(status().isNoContent());
    }

    @Test
    void deleteAnimal_returns_not_found_status_when_animal_not_exists() throws Exception {
        UUID id = TestHelper.TEST_UUID;
        Mockito.doThrow(new AnimalNotFoundException("Animal not found")).when(zooManagementService).deleteAnimalById(id);
        mockMvc.perform(delete("/zoo/animal/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void searchAnimals_returns_ok_status() throws Exception {
        Animal animal = TestHelper.createAnimal();
        SearchAnimalRequest searchAnimalRequest = TestHelper.createSearchAnimalRequest("name", "Lulu");
        Mockito.when(zooManagementService.searchAnimals(any(SearchCriteria.class))).thenReturn(List.of(animal));
        MvcResult result = mockMvc.perform(post("/zoo/search/animals").
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        content(objectMapper.writeValueAsString(searchAnimalRequest)))
                .andExpect(status().isOk())
                .andReturn();
        String actual = result.getResponse().getContentAsString();
        List<AnimalResponse> expectedAnimalResponse = List.of(AnimalResponse.from(animal));
        assertEquals(objectMapper.writeValueAsString(expectedAnimalResponse), actual);
    }
}