package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.exception.AnimalNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ZooManagementService {

    List<Animal> getAnimals();

    Animal createAnimal(AnimalRequest animalRequest);

    void deleteAnimalById(UUID id) throws AnimalNotFoundException;

    Animal feedAnimal(UUID id, double foodWeight) throws AnimalNotFoundException;
}
