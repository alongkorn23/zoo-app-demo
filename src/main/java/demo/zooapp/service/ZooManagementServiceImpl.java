package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalRequest;
import demo.zooapp.domain.Animal;
import demo.zooapp.domain.AnimalSpecification;
import demo.zooapp.entity.AnimalEntity;
import demo.zooapp.exception.AnimalNotFoundException;
import demo.zooapp.model.SearchCriteria;
import demo.zooapp.repository.ZooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ZooManagementServiceImpl implements ZooManagementService {

    private static final Logger logger = LoggerFactory.getLogger(ZooManagementServiceImpl.class);


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
        logger.info("Animal with id {} was successfully created", createdAnimalEntity.getId());
        return Animal.from(createdAnimalEntity);
    }

    @Override
    @Transactional
    public void deleteAnimalById(UUID id) throws AnimalNotFoundException {
        if (zooRepository.existsById(id)) {
            zooRepository.deleteById(id);
            logger.info("Animal with id {} was successfully deleted", id);
        } else {
            String errorMessage = String.format("Could not delete animal. Animal with id %s not found", id);
            logger.error(errorMessage);
            throw new AnimalNotFoundException(errorMessage);
        }
    }

    @Override
    public Animal feedAnimal(UUID id, double foodWeight) throws AnimalNotFoundException {
        AnimalEntity animalEntity = zooRepository.findById(id).orElseThrow(() -> {
                    String errorMessage = String.format("Could not feed animal. Animal with id %s not found", id);
                    logger.error(errorMessage);
                    return new AnimalNotFoundException(errorMessage);
        }
        );
        double newWeight = animalEntity.getWeight() + foodWeight;
        animalEntity.setWeight(newWeight);
        AnimalEntity updatedAnimalEntity = zooRepository.save(animalEntity);
        logger.info("Animal with id {} was successfully fed with food", updatedAnimalEntity.getId());
        return Animal.from(updatedAnimalEntity);
    }

    @Override
    public List<Animal> searchAnimals(SearchCriteria searchCriteria) {
        AnimalSpecification spec = new AnimalSpecification(searchCriteria);
        List<AnimalEntity> animalEntities = zooRepository.findAll(Specification.where(spec));
        return animalEntities.stream().map(Animal::from).toList();
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
