package edu.course.lab04;

import java.util.Objects;

public final class SampleId {
    private final String datasetName;
    private final int sampleNumber;

    public SampleId(String datasetName, int sampleNumber) {
        if (datasetName == null || datasetName.isBlank()) {
            throw new IllegalArgumentException("Имя набора данных не может быть пустым");
        }
        if (sampleNumber < 0) {
            throw new IllegalArgumentException("Номер наблюдения не может быть отрицательным");
        }
        this.datasetName = datasetName;
        this.sampleNumber = sampleNumber;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public int getSampleNumber() {
        return sampleNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SampleId)) {
            return false;
        }
        SampleId that = (SampleId) other;
        return sampleNumber == that.sampleNumber
                && Objects.equals(datasetName, that.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datasetName, sampleNumber);
    }

    @Override
    public String toString() {
        return "SampleId{datasetName='" + datasetName + "', sampleNumber=" + sampleNumber + '}';
    }
}
