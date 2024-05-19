package demo.zooapp.api;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.api.dto.AnimalResponse;
import demo.zooapp.api.dto.FeedAnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.exception.AnimalNotFoundException;
import demo.zooapp.service.ZooManagementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/zoo")
public class ZooManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ZooManagementController.class);

    private final ZooManagementService zooManagementService;

    @Autowired
    public ZooManagementController(ZooManagementService zooManagementService) {
        this.zooManagementService = zooManagementService;
    }

    @GetMapping("/animals")
    ResponseEntity<List<AnimalResponse>> getAnimals() {
        List<Animal> animals = zooManagementService.getAnimals();
        List<AnimalResponse> animalResponse = animals.stream().map(AnimalResponse::from).toList();
        return ResponseEntity.ok(animalResponse);
    }

    @PostMapping("/animal")
    ResponseEntity<AnimalResponse> createAnimal(@RequestBody @Valid AnimalRequest animalRequest) {
        Animal animal = zooManagementService.createAnimal(animalRequest);
        return ResponseEntity.ok(AnimalResponse.from(animal));
    }

    @PutMapping("/feed/animal/{id}")
    ResponseEntity<AnimalResponse> feedAnimal(@PathVariable UUID id, @RequestBody @Valid FeedAnimalRequest feedAnimalRequest) {
        try {
            Animal animal = zooManagementService.feedAnimal(id, feedAnimalRequest.foodWeight());
            return ResponseEntity.ok(AnimalResponse.from(animal));
        } catch (AnimalNotFoundException e) {
            String errorMessage = String.format("Could not feed animal. Cause: %s", e.getMessage());
            logger.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/animal/{id}")
    ResponseEntity<HttpStatus> deleteAnimal(@PathVariable UUID id) {
        try {
            zooManagementService.deleteAnimalById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (AnimalNotFoundException e) {
            String errorMessage = String.format("Could not delete animal. Cause: %s", e.getMessage());
            logger.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
