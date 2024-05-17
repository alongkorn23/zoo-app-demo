package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooManagementServiceImpl implements ZooManagementService {

    @Override
    public List<AnimalResponse> getAllAnimals() {
        return List.of();
    }
}
