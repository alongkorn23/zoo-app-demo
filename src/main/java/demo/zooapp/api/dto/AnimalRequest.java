package demo.zooapp.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AnimalRequest(
        @NotBlank(message = "Name is mandatory")
        String name,

        @DecimalMin(value = "0.1", message = "Invalid value for weight")
        double weight,

        @Min(value = 1, message = "Invalid value for age")
        int ageInYear,

        @NotBlank(message = "Color is mandatory")
        String color,

        @NotBlank(message = "Gender is mandatory")
        String gender,

        @NotBlank(message = "Species is mandatory")
        String species)
        {}
