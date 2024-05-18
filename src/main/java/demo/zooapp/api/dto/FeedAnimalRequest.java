package demo.zooapp.api.dto;

import jakarta.validation.constraints.DecimalMin;

public record FeedAnimalRequest(
        @DecimalMin(value = "0.1", message = "Invalid value for weight")
        double foodWeight) {
}
