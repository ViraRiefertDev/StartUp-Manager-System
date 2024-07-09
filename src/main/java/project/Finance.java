package project;

import java.time.LocalDate;
import java.util.List;

public class Finance {
    private List<Transaction> transactions; //Лист записей о доходах и расходах


    //Внутренний класс Transaction
    private class Transaction{
        private TypeOfTransaction type;//расход или доход
        private double amount; //денежная сумма
        private LocalDate date;//дата транзакции
        private String category; //категория расходов
    }

}
