package demo.zooapp.api.dto;

import demo.zooapp.domain.Animal;
import demo.zooapp.helper.TestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalResponseTest {

    @Test
    void should_map_animal_to_animalResponse_successfully() {
        Animal animal = TestHelper.createAnimal();
        AnimalResponse expected = new AnimalResponse(animal.id(), animal.name(), animal.weight(), animal.ageInYear(), animal.color(), animal.gender(), animal.species());
        AnimalResponse actual = AnimalResponse.from(animal);
        assertEquals(expected, actual);
    }
}