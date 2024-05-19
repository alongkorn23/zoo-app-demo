package demo.zooapp.helper;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.entity.AnimalEntity;

import java.util.UUID;

public class TestHelper {

    public static UUID TEST_UUID = UUID.fromString("7a1bdf93-8e56-4c0b-9281-7ce562e1d8ce");

    public static Animal createAnimal() {
        AnimalEntity animalEntity = createAnimalEntity();
        return new Animal(
                animalEntity.getId(),
                animalEntity.getName(),
                animalEntity.getWeight(),
                animalEntity.getAgeInYear(),
                animalEntity.getColor(),
                animalEntity.getGender(),
                animalEntity.getSpecies());
    }

    public static AnimalEntity createAnimalEntity() {
        AnimalEntity animalEntity = new AnimalEntity(
                "Lulu",
                30,
                9,
                "white",
                "male",
                "Dog");
        animalEntity.setId(TEST_UUID);
        return animalEntity;
    }

    public static AnimalRequest createAnimalRequest() {
        return new AnimalRequest(
                "Maxi",
                10.5,
                5,
                "black",
                "male",
                "Tiger");
    }
}
