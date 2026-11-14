package edu.course.lab04;

/**
 * Минимальное значение, которое хранится в registry.
 * Класс неизменяемый; equals и hashCode здесь не переопределяются,
 * потому что тесты сравнивают сами экземпляры значений.
 */
public final class DataSample {
    private final String description;
    private final double accuracy;

    public DataSample(String description, double accuracy) {
        this.description = description;
        this.accuracy = accuracy;
    }

    public String getDescription() {
        return description;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public String toString() {
        return "DataSample{description='" + description + "', accuracy=" + accuracy + '}';
    }
}
