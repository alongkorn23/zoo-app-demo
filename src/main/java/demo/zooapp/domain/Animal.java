package demo.zooapp.domain;

import demo.zooapp.entity.AnimalEntity;

public record Animal(String name, double weight, int age, String color) {

    public static Animal from(AnimalEntity entity) {
        return new Animal(entity.getName(), entity.getWeight(), entity.getAge(), entity.getColor());
    }
}
