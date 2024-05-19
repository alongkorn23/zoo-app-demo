package demo.zooapp.domain;

import demo.zooapp.entity.AnimalEntity;
import demo.zooapp.helper.TestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void should_map_animalEntity_to_animal_successfully() {
        AnimalEntity entity = TestHelper.createAnimalEntity();
        Animal expected = new Animal(entity.getId(), entity.getName(), entity.getWeight(), entity.getAgeInYear(), entity.getColor(), entity.getGender(), entity.getSpecies());
        Animal actual = Animal.from(entity);
        assertEquals(expected, actual);
    }
}