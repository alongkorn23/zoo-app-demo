package demo.zooapp.api;

import demo.zooapp.api.dto.AnimalResponse;
import demo.zooapp.service.ZooManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zoo")
public class ZooManagementController {

    private final ZooManagementService zooManagementService;

    @Autowired
    public ZooManagementController(ZooManagementService zooManagementService) {
        this.zooManagementService = zooManagementService;
    }

    @GetMapping("/animals")
    ResponseEntity<List<AnimalResponse>> getAnimals() {
        List<AnimalResponse> animals = zooManagementService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

}
