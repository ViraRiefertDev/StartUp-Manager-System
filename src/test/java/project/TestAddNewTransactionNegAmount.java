package project;

public enum TestAddNewTransactionNegAmount {
    TEST_1000_INCOME(-1000,1000,TypeOfTransaction.INCOME),
    Test_50_EXPENCE(-50,1000,TypeOfTransaction.EXPENSE)
    ;


    private final double transactionAmount;
    private final double budget;
    private final TypeOfTransaction type;

    TestAddNewTransactionNegAmount(double transactionAmount, double budget, TypeOfTransaction type) {
        this.transactionAmount = transactionAmount;
        this.budget = budget;
        this.type = type;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public double getBudget() {
        return budget;
    }

    public TypeOfTransaction getType() {
        return type;
    }
}
