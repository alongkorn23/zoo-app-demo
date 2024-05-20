package demo.zooapp.api.dto;

import demo.zooapp.model.SearchCriteria;

public record SearchAnimalRequest(SearchCriteria searchCriteria) {
}