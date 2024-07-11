package project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FinanceTest {
    private Finance finance;

    @BeforeEach
    void setUp(){
        finance = new Finance(1000); //Инициализация объекта finance с начальным бюджетом 1000
    }



    @ParameterizedTest
    @ValueSource(doubles = {500, 10,1000,1,999})
    void testAddNewTransactionIncome(double amount){
        finance.addNewTransaction(TypeOfTransaction.INCOME, amount, LocalDate.now(),"Обучение сотрудников");
        assertEquals((1000+amount),finance.getBalance());
    }

    @ParameterizedTest
    @EnumSource(TestAddNewTransactionNegAmount.class)
    void  testAddNewTransactionNegativeAmount(TestAddNewTransactionNegAmount enumTest){
        finance.addNewTransaction(enumTest.getType(), enumTest.getTransactionAmount(), LocalDate.now(),"Обучение сотрудников");
        assertEquals(enumTest.getBudget(),finance.getBalance());
    }



    @ParameterizedTest
    @ValueSource(doubles = {500, 10,100,1,999})
    void testAddNewTransactionExpence(double amount){
        finance.addNewTransaction(TypeOfTransaction.EXPENSE, amount, LocalDate.now(),"Инвестиции");
        assertEquals((1000-amount),finance.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {2000, 3000,1001,9999})
    void testAddNewTransactionInsuffiecientFunds(double transactionAmount){
        finance.addNewTransaction(TypeOfTransaction.EXPENSE,transactionAmount,LocalDate.now(),"Новое оборудование");
        assertEquals(1000,finance.getBalance());
    }


//    @Test
//    void testGetTotalIncome() {
//        finance.addNewTransaction(TypeOfTransaction.INCOME, 500.0, LocalDate.now(), "Investment");
//        finance.addNewTransaction(TypeOfTransaction.INCOME, 200.0, LocalDate.now(), "Bonus");
//        assertEquals(700.0, finance.getTotalIncome());
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataIncome.csv",numLinesToSkip = 1)
    void testGetTotalIncome(double income1,double income2, double totalIncome){
        finance.addNewTransaction(TypeOfTransaction.INCOME, income1, LocalDate.now(), "Инвестиции");
        finance.addNewTransaction(TypeOfTransaction.INCOME, income2, LocalDate.now(), "Бонус");
        assertEquals(totalIncome,finance.getTotalIncome());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataExpense.csv",numLinesToSkip = 1)
    void testGetTotalExpense(double extense1, double extense2, double totalExtense){
        finance.addNewTransaction(TypeOfTransaction.EXPENSE, extense1, LocalDate.now(), "Маркетинг");
        finance.addNewTransaction(TypeOfTransaction.EXPENSE, extense2, LocalDate.now(), "Оборудование");
        assertEquals(totalExtense,finance.getTotalExpense());
    }

//
//    @Test
//    void testGetTotalExpense() {
//        finance.addNewTransaction(TypeOfTransaction.EXPENSE, 100.0, LocalDate.now(), "Supplies");
//        finance.addNewTransaction(TypeOfTransaction.EXPENSE, 300.0, LocalDate.now(), "Marketing");
//        assertEquals(400.0, finance.getTotalExpense());
//    }
}