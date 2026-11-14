package edu.course.lab04;

import java.util.Objects;

/**
 * Учебный ключ с изменяемым состоянием. Существует только в тестах:
 * он показывает, почему ключ HashMap нельзя менять после добавления.
 */
final class MutableSampleKey {
    private String datasetName;
    private int sampleNumber;

    MutableSampleKey(String datasetName, int sampleNumber) {
        this.datasetName = datasetName;
        this.sampleNumber = sampleNumber;
    }

    void setSampleNumber(int sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MutableSampleKey)) {
            return false;
        }
        MutableSampleKey that = (MutableSampleKey) other;
        return sampleNumber == that.sampleNumber
                && Objects.equals(datasetName, that.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datasetName, sampleNumber);
    }
}
