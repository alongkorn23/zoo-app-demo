package demo.zooapp.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Animal")
public class AnimalEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    double weight;

    @Column(nullable = false)
    int age;

    @Column(nullable = false)
    String color;

    public AnimalEntity(UUID id, String name, double weight, int age, String color) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.color = color;
    }

    public AnimalEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ZooEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
