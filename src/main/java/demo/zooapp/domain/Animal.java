package demo.zooapp.domain;

import demo.zooapp.entity.AnimalEntity;

public record Animal(String name, double weight, int ageInYear, String color, String gender, String species) {

    // Map entity to domain model
    public static Animal from(AnimalEntity entity) {
        return new Animal(entity.getName(), entity.getWeight(), entity.getAgeInYear(), entity.getColor(), entity.getGender(), entity.getSpecies());
    }
}
