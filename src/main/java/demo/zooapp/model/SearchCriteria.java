package demo.zooapp.model;

import demo.zooapp.exception.SearchCriteriaException;

public record SearchCriteria(String key, String value) {

    public SearchCriteria {
        if (key.isBlank()) {
            throw new SearchCriteriaException("Invalid value. Key cannot be blank.");
        }
        if (value.isBlank()) {
            throw new SearchCriteriaException("Invalid value. Value cannot be blank.");
        }
    }

}
