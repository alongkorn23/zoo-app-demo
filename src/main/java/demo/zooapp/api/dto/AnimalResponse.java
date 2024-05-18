package demo.zooapp.api.dto;

import demo.zooapp.domain.Animal;

import java.util.UUID;

public record AnimalResponse(UUID id, String name, double weight, int ageInYear, String color, String gender, String species) {

    // Map domain model to response model
    public static AnimalResponse from(Animal animal) {
        return new AnimalResponse(
                animal.id(),
                animal.name(),
                animal.weight(),
                animal.ageInYear(),
                animal.color(),
                animal.gender(),
                animal.species());
    }
}
