package demo.zooapp.model;

import demo.zooapp.exception.SearchCriteriaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchCriteriaTest {

    @Test
    void should_create_successfully() {
        assertDoesNotThrow(() -> {
            SearchCriteria searchCriteria = new SearchCriteria("name", "Lulu");
            assertNotNull(searchCriteria);
        });
    }

    @Test
    void should_throw_exception_when_key_is_blank() {
        assertThrows(SearchCriteriaException.class, () -> {
            new SearchCriteria("", "Lulu");
        });
    }

    @Test
    void should_throw_exception_when_value_is_blank() {
        assertThrows(SearchCriteriaException.class, () -> {
            new SearchCriteria("name", "");
        });
    }

}