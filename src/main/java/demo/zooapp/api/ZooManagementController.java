package demo.zooapp.api;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.api.dto.AnimalResponse;
import demo.zooapp.domain.Animal;
import demo.zooapp.service.ZooManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/zoo")
public class ZooManagementController {

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

    @DeleteMapping("/animal/{id}")
    ResponseEntity<HttpStatus> deleteAnimal(@PathVariable UUID id) {
        zooManagementService.deleteAnimalById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
