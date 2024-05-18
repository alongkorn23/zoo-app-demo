package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.domain.Animal;

import java.util.List;
import java.util.UUID;

public interface ZooManagementService {

    List<Animal> getAnimals();

    Animal createAnimal(AnimalRequest animalRequest);

    void deleteAnimalById(UUID id);
}
