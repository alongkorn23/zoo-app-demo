package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.entity.AnimalEntity;
import demo.zooapp.exception.AnimalNotFoundException;
import demo.zooapp.helper.TestHelper;
import demo.zooapp.model.SearchCriteria;
import demo.zooapp.repository.ZooRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZooManagementServiceImplTest {

    @InjectMocks
    ZooManagementServiceImpl zooManagementServiceImpl;

    @Mock
    ZooRepository zooRepository;

    @Test
    void getAnimals_returns_animal_list() {
        AnimalEntity animalEntity = TestHelper.createAnimalEntity();
        when(zooRepository.findAll()).thenReturn(List.of(animalEntity));
        List<Animal> animals = zooManagementServiceImpl.getAnimals();
        assertEquals(1, animals.size());
        Assertions.assertIterableEquals(List.of(Animal.from(animalEntity)), animals);
    }

    @Test
    void createAnimal_returns_animal() {
        AnimalEntity createdanimalEntity = TestHelper.createAnimalEntity();
        AnimalRequest animalRequest = TestHelper.createAnimalRequest();
        when(zooRepository.save(any())).thenReturn(createdanimalEntity);
        Animal expected = Animal.from(createdanimalEntity);
        Animal actual = zooManagementServiceImpl.createAnimal(animalRequest);
        assertEquals(expected, actual);
    }

    @Test
    void deleteAnimal_throws_animalNotFoundException_when_animal_not_exists() {
        when(zooRepository.existsById(any())).thenReturn(false);
        assertThrows(AnimalNotFoundException.class, () -> {
            zooManagementServiceImpl.deleteAnimalById(any());
        });
        verify(zooRepository, times(0)).deleteById(any());
    }

    @Test
    void deleteAnimal_does_not_throw_animalNotFoundException_when_animal_exists() {
        when(zooRepository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(() -> {
            zooManagementServiceImpl.deleteAnimalById(any());
        });
        verify(zooRepository, times(1)).deleteById(any());
    }

    @Test
    void feedAnimal_returns_animal_when_animal_exists() throws AnimalNotFoundException {
        AnimalEntity animalEntity = TestHelper.createAnimalEntity();
        AnimalEntity updatedAnimalEntity = TestHelper.createAnimalEntity();
        updatedAnimalEntity.setWeight(500);
        when(zooRepository.findById(any())).thenReturn(Optional.of(animalEntity));
        when(zooRepository.save(any())).thenReturn(updatedAnimalEntity);
        Animal expected = Animal.from(updatedAnimalEntity);
        Animal actual = zooManagementServiceImpl.feedAnimal(any(), 500);
        assertEquals(expected, actual);
    }

    @Test
    void feedAnimal_throws_animalNotFoundException_when_animal_not_exists() {
        when(zooRepository.findById(TestHelper.TEST_UUID)).thenReturn(Optional.empty());
        assertThrows(AnimalNotFoundException.class, () -> {
            zooManagementServiceImpl.feedAnimal(TestHelper.TEST_UUID, 10.5);
        });

    }

    @Test
    void searchAnimals_returns_animal_list() {
        AnimalEntity animalEntity1 = TestHelper.createAnimalEntity();
        List<Animal> expectedAnimals = List.of(Animal.from(animalEntity1));
        when(zooRepository.findAll(any(Specification.class))).thenReturn(List.of(animalEntity1));
        List<Animal> actualAnimals = zooManagementServiceImpl.searchAnimals(any(SearchCriteria.class));
        assertNotNull(actualAnimals);
        assertEquals(1, actualAnimals.size());
        Assertions.assertIterableEquals(expectedAnimals, actualAnimals);
    }
}