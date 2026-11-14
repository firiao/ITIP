package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

    @Test
    void valueIsFoundByAnotherBookWithSameIsbn() {
        Book stored = new Book("978-5-00000-001-1", "Первый том");
        Book lookup = new Book("978-5-00000-001-1", "Другое издание");

        Map<Book, String> shelf = new HashMap<>();
        shelf.put(stored, "полка 1");

        assertEquals("полка 1", shelf.get(lookup));
        assertEquals(stored.hashCode(), lookup.hashCode());
    }

    @Test
    void equalBooksHaveSameHashCode() {
        Book first = new Book("978-5-00000-001-1", "Книга");
        Book second = new Book("978-5-00000-001-1", "Совсем другое название");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book first = new Book("978-5-00000-001-1", "Книга");
        Book second = new Book("978-5-00000-002-8", "Книга");

        assertNotEquals(first, second);
        assertFalse(first.equals(second));
    }

    @Test
    void equalsReturnsFalseForNullAndOtherType() {
        Book book = new Book("978-5-00000-001-1", "Книга");

        assertTrue(book.equals(book));
        assertFalse(book.equals(null));
        assertFalse(book.equals("978-5-00000-001-1"));
    }
}
