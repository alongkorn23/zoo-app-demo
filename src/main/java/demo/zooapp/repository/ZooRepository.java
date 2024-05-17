package demo.zooapp.repository;

import demo.zooapp.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ZooRepository extends JpaRepository<AnimalEntity, UUID> {
}
