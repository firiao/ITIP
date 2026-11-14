package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SampleIdTest {

    @Test
    void idsWithSameDatasetAndNumberAreEqual() {
        SampleId first = new SampleId("iris", 7);
        SampleId second = new SampleId("iris", 7);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void idsDifferWhenDatasetOrNumberDiffer() {
        SampleId base = new SampleId("iris", 7);

        assertNotEquals(base, new SampleId("wine", 7));
        assertNotEquals(base, new SampleId("iris", 8));
    }

    @Test
    void equalsReturnsFalseForNullAndOtherType() {
        SampleId id = new SampleId("iris", 7);

        assertFalse(id.equals(null));
        assertFalse(id.equals("iris/7"));
    }

    @Test
    void blankDatasetNameIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new SampleId(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("   ", 1));
    }

    @Test
    void negativeSampleNumberIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new SampleId("iris", -1));
    }
}
