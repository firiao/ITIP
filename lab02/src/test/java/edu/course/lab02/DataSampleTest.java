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
}