package demo.zooapp.domain;

import demo.zooapp.entity.AnimalEntity;
import demo.zooapp.model.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AnimalSpecification implements Specification<AnimalEntity> {
    private final SearchCriteria searchCriteria;

    public AnimalSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<AnimalEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.key().equalsIgnoreCase("name")) {
            return criteriaBuilder.equal(root.get("name"), searchCriteria.value());
        } else if (searchCriteria.key().equalsIgnoreCase("weight")) {
            return criteriaBuilder.equal(root.get("weight"), Double.parseDouble(searchCriteria.value()));
        } else if (searchCriteria.key().equalsIgnoreCase("ageInYear")) {
            return criteriaBuilder.equal(root.get("ageInYear"), Integer.parseInt(searchCriteria.value()));
        } else if (searchCriteria.key().equalsIgnoreCase("color")) {
            return criteriaBuilder.equal(root.get("color"), searchCriteria.value());
        } else if (searchCriteria.key().equalsIgnoreCase("gender")) {
            return criteriaBuilder.equal(root.get("gender"), searchCriteria.value());
        } else if (searchCriteria.key().equalsIgnoreCase("species")) {
            return criteriaBuilder.equal(root.get("species"), searchCriteria.value());
        }
        return null;
    }

}