package edu.course.lab02;

public class BankAccount {
    // Приватное поле баланса
    private int balance;

    // Конструктор
    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                "Начальный баланс не может быть отрицательным: " + initialBalance
            );
        }
        this.balance = initialBalance;
    }

    // Метод внесения
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма внесения обязана быть положительной и не равна нулю" + amount
            );
        }
        balance += amount;
    }


    // Метод снятия
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма снятия обязана быть положительной и не равна нулю" + amount
            );
        }
        if (amount > balance) {
            throw new IllegalArgumentException(
                "Сумма снятия обязана быть меньше суммы на балансе"
            );
        }
        balance -= amount;
    }


    /**  Геттер без сеттера - чтобы
    баланс мог меняться только 
    через уже существующие методы */ 
    public int getBalance() {
        return balance;
    }
}
