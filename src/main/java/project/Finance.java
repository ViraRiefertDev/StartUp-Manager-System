package project;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Finance implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Finance.class);
    @Getter
    private double balance;
    private List<Transaction> transactions; //Лист записей о доходах и расходах

    public Finance(double budget) {
        this.balance = budget;
        this.transactions = new ArrayList<>();
    }

    //Внутренний класс Transaction
    private class Transaction implements Serializable {
        private static final long serialVersionUID = 1L;
        private TypeOfTransaction type;//расход или доход
        private double amount; //денежная сумма
        private LocalDate date;//дата транзакции
        private String category; //категория расходов

        public Transaction(TypeOfTransaction type, double amount, LocalDate date, String category) {
            this.type = type;
            this.amount = amount;
            this.date = date;
            this.category = category;
        }

        @Override
        public String toString() {
            return "++++++++++++++\n" + "Тип транзакции " + this.type + " - Сумма: " + amount + ", - Дата: "
                    + date + "\nКатегория: " + category +"\n++++++++++++++";
        }
    }



    //метод добавления новой транзакции в лист
    public void addNewTransaction(TypeOfTransaction type, double amount, LocalDate date, String category) {
        if (amount < 0) {
            LOGGER.error("Денежная сумма не может быть < 0");
        } else {
            if(type == TypeOfTransaction.EXPENSE&&balance<=amount){
                LOGGER.error("Транзакция невозможно! Не хватает денег на счету!");
            }else{
                if(type == TypeOfTransaction.EXPENSE){
                    balance = balance-amount;
                    LOGGER.info("Расход категории " + category + " на сумму " + amount +" был успешно добавлен в список");
                }
                else{
                    balance = balance+amount;
                    LOGGER.info("Доход категории " + category + " на сумму " + amount +" был успешно добавлен в список");
                }
                Transaction transaction = new Transaction(type, amount, date, category);
                transactions.add(transaction);
                LOGGER.info("Актуальный балланс " + balance);

            }

        }
    }

    //метод печати всех транзакций
    public void printAllTransaction(){
        if(transactions.isEmpty()){
            System.out.println("Не было произведено еще ни одной транзакции!");
        }
        else{
            transactions.stream().forEach(System.out::println);
        }
    }

    //метод возвращает общую сумму доходов по всем транзакциям проекта
    public double getTotalIncome(){
        double totalIncome = 0;
        for (Transaction transaction : transactions) {
            if(transaction.type==TypeOfTransaction.INCOME){
              totalIncome += transaction.amount;
            }
        }
        return totalIncome;
    }

    //метод возвращает общую сумму расходов по всем транзакциям проекта
    public double getTotalExpense(){
        double totalExpense = 0;
        for (Transaction transaction : transactions) {
            if(transaction.type==TypeOfTransaction.EXPENSE){
                totalExpense += transaction.amount;
            }
        }
        return totalExpense;
    }


}
