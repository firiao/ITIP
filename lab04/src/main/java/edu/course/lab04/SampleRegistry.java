package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Реестр наблюдений на основе композиции с HashMap.
 * Ключи SampleId неизменяемы, поэтому поиск не зависит от последующих изменений.
 */
public final class SampleRegistry {
    private final Map<SampleId, DataSample> storage = new HashMap<>();

    /**
     * Добавляет значение или заменяет его, если равный ключ уже есть.
     *
     * @return предыдущее значение или null, если ключа не было
     */
    public DataSample put(SampleId id, DataSample sample) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(sample, "sample");
        return storage.put(id, sample);
    }

    public DataSample get(SampleId id) {
        Objects.requireNonNull(id, "id");
        return storage.get(id);
    }

    public DataSample remove(SampleId id) {
        Objects.requireNonNull(id, "id");
        return storage.remove(id);
    }

    public boolean containsKey(SampleId id) {
        Objects.requireNonNull(id, "id");
        return storage.containsKey(id);
    }

    public int size() {
        return storage.size();
    }
}
