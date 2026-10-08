package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataSampleTest {

    // ========== Группа 1: успешное создание ==========

    @Test
    void createsValidSample() {
        double[] features = {1.0, 2.0, 3.0};
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, features);

        assertEquals(new Identificator("s1"), sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.RAW, sample.getStatus());
        assertFalse(sample.isReady());
    }

    // ========== Группа 2: инварианты конструктора ==========

    @Test
    void throwsWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(null, "cat", SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void throwsWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(new Identificator(""), "cat", SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void throwsWhenLabelIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(new Identificator("s1"), null, SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void throwsWhenLabelIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(new Identificator("s1"), "", SampleStatus.RAW, new double[]{1.0}));
    }

    @Test
    void throwsWhenStatusIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(new Identificator("s1"), "cat", null, new double[]{1.0}));
    }

    @Test
    void throwsWhenFeaturesIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, null));
    }

    @Test
    void throwsWhenFeaturesIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
            new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[0]));
    }

    // ========== Группа 3: changeStatus ==========

    @Test
    void changesStatus() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[]{1.0});
        sample.changeStatus(SampleStatus.READY);
        assertEquals(SampleStatus.READY, sample.getStatus());
    }

    @Test
    void throwsWhenChangingStatusToNull() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[]{1.0});
        assertThrows(IllegalArgumentException.class, () ->
            sample.changeStatus(null));
    }

    // ========== Группа 4: isReady ==========

    @Test
    void isReadyWhenStatusIsReady() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.READY, new double[]{1.0});
        assertTrue(sample.isReady());
    }

    @Test
    void isNotReadyWhenStatusIsNotReady() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[]{1.0});
        assertFalse(sample.isReady());
    }

    // ========== Группа 5: защита массива (defensive copy) ==========

    @Test
    void constructorCopiesFeaturesArray() {
        double[] original = {1.0, 2.0, 3.0};
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, original);

        original[0] = 999.0;   // меняем ИСХОДНЫЙ массив

        assertEquals(1.0, sample.getFeatures()[0]);
    }

    @Test
    void getFeaturesReturnsCopy() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[]{1.0, 2.0});

        double[] copy = sample.getFeatures();
        copy[0] = 999.0;   // меняем КОПИЮ

        assertEquals(1.0, sample.getFeatures()[0]);
    }

    // ========== Группа 6: averageFeature ==========

    @Test
    void computesAverageOfFeatures() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[]{1.0, 2.0, 3.0});
        assertEquals(2.0, sample.averageFeature());
    }

    @Test
    void averageOfSingleFeatureEqualsIt() {
        DataSample sample = new DataSample(new Identificator("s1"), "cat", SampleStatus.RAW, new double[]{5.0});
        assertEquals(5.0, sample.averageFeature());
    }

    // ========== Группа 7: Доп. задание тесты =========
    @Test
    void normalizesFeaturesToZeroOneRange() {
    DataSample sample = new DataSample(
        new Identificator("s1"), "cat", SampleStatus.RAW,
        new double[]{10.0, 20.0, 30.0}
    );
    double[] normalized = sample.normalizedFeatures();
    assertEquals(0.0, normalized[0], 0.0001);
    assertEquals(0.5, normalized[1], 0.0001);
    assertEquals(1.0, normalized[2], 0.0001);
}

    @Test
    void normalizationDoesNotChangeOriginalFeatures() {
    DataSample sample = new DataSample(
        new Identificator("s1"), "cat", SampleStatus.RAW,
        new double[]{10.0, 20.0, 30.0}
    );
    sample.normalizedFeatures();   // вызываем, но не сохраняем результат

    double[] original = sample.getFeatures();
    assertEquals(10.0, original[0], 0.0001);
    assertEquals(20.0, original[1], 0.0001);
    assertEquals(30.0, original[2], 0.0001);
}

    @Test
    void normalizationOfIdenticalValuesReturnsZeros() {
    DataSample sample = new DataSample(
        new Identificator("s1"), "cat", SampleStatus.RAW,
        new double[]{5.0, 5.0, 5.0}
    );
    double[] normalized = sample.normalizedFeatures();
    assertEquals(0.0, normalized[0], 0.0001);
    assertEquals(0.0, normalized[1], 0.0001);
    assertEquals(0.0, normalized[2], 0.0001);
}




}