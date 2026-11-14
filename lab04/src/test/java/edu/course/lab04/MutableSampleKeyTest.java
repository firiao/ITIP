package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MutableSampleKeyTest {

    @Test
    void changingKeyFieldBreaksLookup() {
        MutableSampleKey key = new MutableSampleKey("iris", 1);
        Map<MutableSampleKey, String> map = new HashMap<>();
        map.put(key, "значение");

        assertEquals("значение", map.get(key));

        key.setSampleNumber(2);

        assertNull(map.get(key));
        assertFalse(map.containsKey(key));
        assertEquals(1, map.size());
        assertTrue(map.containsValue("значение"));
        assertNull(map.get(new MutableSampleKey("iris", 2)));

        key.setSampleNumber(1);

        assertEquals("значение", map.get(key));
    }
}
