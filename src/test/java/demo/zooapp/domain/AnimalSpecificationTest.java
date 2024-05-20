package demo.zooapp.domain;

import demo.zooapp.entity.AnimalEntity;
import demo.zooapp.model.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalSpecificationTest {
    @Mock
    private Root<AnimalEntity> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @Mock
    private Path<Object> path;

    @Mock
    private Predicate predicate;

    @Test
    void should_create_where_clause_search_query_name() {
        SearchCriteria criteria = new SearchCriteria("name", "Lulu");
        AnimalSpecification spec = new AnimalSpecification(criteria);
        when(root.get("name")).thenReturn(path);
        when(builder.equal(path, "Lulu")).thenReturn(predicate);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(root).get("name");
        verify(builder).equal(path, "Lulu");
    }

    @Test
    void should_create_where_clause_search_query_weight() {
        SearchCriteria criteria = new SearchCriteria("weight", "10.5");
        AnimalSpecification spec = new AnimalSpecification(criteria);
        when(root.get("weight")).thenReturn(path);
        when(builder.equal(path, Double.parseDouble("10.5"))).thenReturn(predicate);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(root).get("weight");
        verify(builder).equal(path, Double.parseDouble("10.5"));
    }

    @Test
    void should_create_where_clause_search_query_ageInYear() {
        SearchCriteria criteria = new SearchCriteria("ageInYear", "5");
        AnimalSpecification spec = new AnimalSpecification(criteria);
        when(root.get("ageInYear")).thenReturn(path);
        when(builder.equal(path, Integer.parseInt("5"))).thenReturn(predicate);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(root).get("ageInYear");
        verify(builder).equal(path, Integer.parseInt("5"));
    }

    @Test
    void should_create_where_clause_search_query_color() {
        SearchCriteria criteria = new SearchCriteria("color", "white");
        AnimalSpecification spec = new AnimalSpecification(criteria);
        when(root.get("color")).thenReturn(path);
        when(builder.equal(path, "white")).thenReturn(predicate);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(root).get("color");
        verify(builder).equal(path, "white");
    }

    @Test
    void should_create_where_clause_search_query_gender() {
        SearchCriteria criteria = new SearchCriteria("gender", "male");
        AnimalSpecification spec = new AnimalSpecification(criteria);
        when(root.get("gender")).thenReturn(path);
        when(builder.equal(path, "male")).thenReturn(predicate);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(root).get("gender");
        verify(builder).equal(path, "male");
    }

    @Test
    void should_create_where_clause_search_query_species() {
        SearchCriteria criteria = new SearchCriteria("species", "Dog");
        AnimalSpecification spec = new AnimalSpecification(criteria);
        when(root.get("species")).thenReturn(path);
        when(builder.equal(path, "Dog")).thenReturn(predicate);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNotNull(actualPredicate);
        assertEquals(predicate, actualPredicate);
        verify(root).get("species");
        verify(builder).equal(path, "Dog");
    }

    @Test
    void should_return_null_when_search_field_not_exists() {
        SearchCriteria criteria = new SearchCriteria("test", "Dog");
        AnimalSpecification spec = new AnimalSpecification(criteria);

        Predicate actualPredicate = spec.toPredicate(root, query, builder);

        assertNull(actualPredicate);
        verify(root, times(0)).get("test");
        verify(builder, times(0)).equal(path, "Dog");
    }
}