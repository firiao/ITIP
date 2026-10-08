package edu.course.lab02;

public class DataSample {

    // Создание полей
    private final Identificator id;
    private final String label;
    private SampleStatus status;
    private final double[] features; 

    // Конструктор
    public DataSample (Identificator id, String label, SampleStatus status, double[] features) {
        
        // Проверки на правильность введённых значений (инвариант)
        
        if (id == null) {
            throw new IllegalArgumentException("id должен быть не null!");
        }
        
        if (label == null) {
            throw new IllegalArgumentException("label должен быть не null!");
        }
        if (label.isEmpty()) {
            throw new IllegalArgumentException("label должен быть не пустым!");
        }

        if (features == null) {
            throw new IllegalArgumentException("features должен быть не null!");
        }
        if (features.length == 0) {
            throw new IllegalArgumentException("features должен быть не пустым!");
        }
        if (status == null) {
            throw new IllegalArgumentException("status должен быть не null!");
        }

        // Присвоение
        
        this.id = id;
        this.label = label;
        this.status = status;
        this.features = features.clone();
    }
    
    // Метод, меняющий статус у объекта
    
    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Новый статус не может быть null");
        }
        this.status = newStatus;
    }

    // isReady метод
    
    public boolean isReady() {
       return status == SampleStatus.READY;
    }

    // getter для полей

    public double[] getFeatures() {
        return features.clone();
    }

    public Identificator getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    // метод, возвращающий ср. арифметическое features

    public double averageFeature() {
        double sum = 0.0;
        for(double f : features) {
            sum += f;
        }
        return sum / features.length;
    }

    // Метод нормализации данных

    public double[] normalizedFeatures() {
    double min = features[0];
    double max = features[0];
    for (double f : features) {
        if (f < min) min = f;
        if (f > max) max = f;
    }

    // Если все элементы одинаковые, диапазон нулевой
    if (max == min) {
        return new double[features.length];   // массив нулей
    }

    double[] normalized = new double[features.length];
    for (int i = 0; i < features.length; i++) {
        normalized[i] = (features[i] - min) / (max - min);
    }
    return normalized;
}
}
