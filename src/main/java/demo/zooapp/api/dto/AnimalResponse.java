package demo.zooapp.api.dto;

import demo.zooapp.domain.Animal;

public record AnimalResponse(String name, double weight, int ageInYear, String color, String gender, String species) {

    // Map domain model to response model
    public static AnimalResponse from(Animal animal) {
        return new AnimalResponse(animal.name(), animal.weight(), animal.ageInYear(), animal.color(), animal.gender(), animal.species());
    }
}
