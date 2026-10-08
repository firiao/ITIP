package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    @Test 
    void throwsWithdrawExceptionForNegativeBalance() {
        BankAccount TestBankAccount = new BankAccount(500);
        assertThrows(IllegalArgumentException.class, () -> {
            TestBankAccount.withdraw(1000);
    });
    }

    @Test
    void showsCorrectBalanceAfterDeposit() {
        BankAccount TestBankAccount = new BankAccount(1000);
        TestBankAccount.withdraw(500);
        assertEquals(500, TestBankAccount.getBalance());
    }
}
