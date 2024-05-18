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
    int ageInYear;

    @Column(nullable = false)
    String color;

    @Column(nullable = false)
    String gender;

    @Column(nullable = false)
    String species;

    public AnimalEntity(String name, double weight, int ageInYear, String color, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.ageInYear = ageInYear;
        this.color = color;
        this.gender = gender;
        this.species = species;
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

    public int getAgeInYear() {
        return ageInYear;
    }

    public void setAgeInYear(int ageInYear) {
        this.ageInYear = ageInYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", ageInYear=" + ageInYear +
                ", color='" + color + '\'' +
                ", gender='" + gender + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}
