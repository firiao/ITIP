package edu.course.lab02;

public record Identificator(String value) {
    public Identificator {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Identificator не может быть null или пустым");
        }
    }
}