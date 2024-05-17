package demo.zooapp.service;

import demo.zooapp.api.dto.AnimalResponse;

import java.util.List;

public interface ZooManagementService {

    List<AnimalResponse> getAllAnimals();
}
