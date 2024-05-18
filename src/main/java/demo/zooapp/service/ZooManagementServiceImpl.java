package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.entity.AnimalEntity;
import demo.zooapp.exception.AnimalNotFoundException;
import demo.zooapp.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ZooManagementServiceImpl implements ZooManagementService {

    private final ZooRepository zooRepository;

    @Autowired
    public ZooManagementServiceImpl(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }

    @Override
    public List<Animal> getAnimals() {
        List<AnimalEntity> animalEntities = zooRepository.findAll();
        return animalEntities.stream().map(Animal::from).toList();
    }

    @Override
    @Transactional
    public Animal createAnimal(AnimalRequest animalRequest) {
        AnimalEntity createdAnimalEntity = saveAnimal(animalRequest);
        return Animal.from(createdAnimalEntity);
    }

    @Override
    @Transactional
    public void deleteAnimalById(UUID id) {
        zooRepository.deleteById(id);
    }

    @Override
    public Animal feedAnimal(UUID id, double foodWeight) throws AnimalNotFoundException {
        AnimalEntity animalEntity = zooRepository.findById(id).orElseThrow(() ->
                new AnimalNotFoundException(String.format("Animal with id %s not found", id)));
        double newWeight = animalEntity.getWeight() + foodWeight;
        animalEntity.setWeight(newWeight);
        AnimalEntity updatedAnimalEntity = zooRepository.save(animalEntity);
        return Animal.from(updatedAnimalEntity);
    }

    private AnimalEntity saveAnimal(AnimalRequest animalRequest) {
        AnimalEntity animalEntity = new AnimalEntity(
                animalRequest.name(),
                animalRequest.weight(),
                animalRequest.ageInYear(),
                animalRequest.color(),
                animalRequest.gender(),
                animalRequest.species());
        return zooRepository.save(animalEntity);
    }
}
