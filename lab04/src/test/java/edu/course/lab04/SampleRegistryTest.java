package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SampleRegistryTest {

    // --- Обычные операции registry ---

    @Test
    void putStoresValueAndSizeGrows() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id = new SampleId("iris", 1);
        DataSample sample = new DataSample("первое наблюдение", 0.91);

        assertNull(registry.put(id, sample));
        assertTrue(registry.containsKey(id));
        assertSame(sample, registry.get(id));
        assertEquals(1, registry.size());
    }

    @Test
    void removeDeletesOnlyRequestedEntry() {
        SampleRegistry registry = new SampleRegistry();
        SampleId firstId = new SampleId("iris", 1);
        SampleId secondId = new SampleId("iris", 2);
        DataSample first = new DataSample("первое", 0.80);
        DataSample second = new DataSample("второе", 0.95);
        registry.put(firstId, first);
        registry.put(secondId, second);

        assertSame(first, registry.remove(firstId));
        assertNull(registry.get(firstId));
        assertEquals(1, registry.size());
        assertSame(second, registry.get(secondId));
    }

    @Test
    void missingKeyIsReportedAsAbsent() {
        SampleRegistry registry = new SampleRegistry();
        SampleId missing = new SampleId("wine", 9);

        assertNull(registry.get(missing));
        assertFalse(registry.containsKey(missing));
        assertNull(registry.remove(missing));
        assertEquals(0, registry.size());
    }

    // --- Эксперимент 1: другой экземпляр равного ключа ---

    @Test
    void equalKeyInstanceFindsSameValue() {
        SampleRegistry registry = new SampleRegistry();
        SampleId storedKey = new SampleId("iris", 1);
        SampleId lookupKey = new SampleId("iris", 1);
        DataSample sample = new DataSample("наблюдение", 0.93);

        registry.put(storedKey, sample);

        assertNotEquals(System.identityHashCode(storedKey), System.identityHashCode(lookupKey));
        assertEquals(storedKey, lookupKey);
        assertSame(sample, registry.get(lookupKey));
    }

    // --- Эксперимент 2: повторное добавление с равным ключом ---

    @Test
    void repeatedPutWithEqualKeyReplacesValueWithoutGrowth() {
        SampleRegistry registry = new SampleRegistry();
        DataSample first = new DataSample("старое", 0.60);
        DataSample second = new DataSample("новое", 0.99);

        registry.put(new SampleId("iris", 1), first);
        DataSample replaced = registry.put(new SampleId("iris", 1), second);

        assertSame(first, replaced);
        assertEquals(1, registry.size());
        assertSame(second, registry.get(new SampleId("iris", 1)));
    }

    // --- Эксперимент 3: коллизия не означает равенство ---

    @Test
    void collidingKeysKeepDifferentValues() {
        SampleId firstKey = new SampleId("Aa", 1);
        SampleId secondKey = new SampleId("BB", 1);

        // "Aa" и "BB" имеют одинаковый String.hashCode(), поэтому ключи
        // попадают в один bucket. Это коллизия, а не логическое равенство.
        assertEquals(firstKey.hashCode(), secondKey.hashCode());
        assertNotEquals(firstKey, secondKey);

        SampleRegistry registry = new SampleRegistry();
        DataSample first = new DataSample("набор Aa", 0.70);
        DataSample second = new DataSample("набор BB", 0.80);
        registry.put(firstKey, first);
        registry.put(secondKey, second);

        assertEquals(2, registry.size());
        assertSame(first, registry.get(firstKey));
        assertSame(second, registry.get(secondKey));
    }

    // --- Проверки аргументов ---

    @Test
    void nullKeyOrValueIsRejected() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id = new SampleId("iris", 1);
        DataSample sample = new DataSample("наблюдение", 0.90);

        assertThrows(NullPointerException.class, () -> registry.put(null, sample));
        assertThrows(NullPointerException.class, () -> registry.put(id, null));
    }
}
