package project;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.jar.JarOutputStream;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Добро пожаловать в систему управления стартапом!");
        int countOfOperations =4;
        boolean exit = false;
        while(!exit){
            System.out.println("*********************************");
            System.out.println("Выберите действие:");
            System.out.println("0. Exit");
            System.out.println("1. Управление проектами");
            System.out.println("2. Управление сотрудниками");
            System.out.println("3. Управление финансами");
            System.out.println("4. Управление задачами");
            System.out.println("**********************************");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();
            boolean isRigthChoise = false;
            while (!isRigthChoise) {
                int choise = scanner.nextInt();
                if (!(choise >= 0 && choise <= countOfOperations)) {
                    System.out.println("Введите число от 0 до " + countOfOperations);
                } else {
                    isRigthChoise = true;
                    switch (choise){
                        case 0: {exit = true;
                            Project.serializeProjects();
                            Employee.serializeProjects();
                            System.out.println("До свидания!");
                            break;}
                        case 1: managerProjects();break;
                        case 2: manageEmployee();break;
                        case 3: manageFinance();break;
                        case 4: manageTasks();break;


                    }

                }
            }
        }
    }

    private static void managerProjects() {
        int countOfOptions = 3;
        boolean exit = false;
        while (!exit) {
            System.out.println("*********************************");
            System.out.println("Выберите действие:");
            System.out.println("0. Exit");
            System.out.println("1. Добавить новый проект");
            System.out.println("2. Просмотр всех проектов.");
            System.out.println("3. Обновить информацию о проекте");
            System.out.println("**********************************");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();
            boolean isRigthChoise = false;
            while (!isRigthChoise) {
                int choise = scanner.nextInt();
                if (!(choise>=0 && choise<= countOfOptions)) {
                    System.out.println("Введите число от 0 до " + countOfOptions);
                } else {
                    isRigthChoise = true;
                    switch (choise) {
                        case 0:{
                            exit = true;
                            break;
                        }
                        case 1: {
                            System.out.println("Вы выбрали добавление нового проекта.");
                            addNewProject();
                            break;
                        }
                        case 2: {
                            System.out.println("Вы выбрали просмотр всех проектов");
                            System.out.println("*********************************");
                            System.out.println("Список всех проектов");
                            Project.displayAllProjects();
                            break;
                        }
                        case 3:{
                            System.out.println("Вы выбрали обновление информации о проекте.");
                            updateProjectInfo();
                            break;
                        }

                    }
                }


            }
        }
    }

    private static void manageEmployee(){
        int countOfOptions = 3;
        boolean exit = false;
        while (!exit) {
            System.out.println("*********************************");
            System.out.println("Выберите действие:");
            System.out.println("0. Exit");
            System.out.println("1. Добавить нового сотрудника");
            System.out.println("2. Обновление должности сотрудника.");
            System.out.println("3. Просмотр информации о всех сотрудниках");
            System.out.println("**********************************");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();
            boolean isRigthChoise = false;
            while (!isRigthChoise) {
                int choise = scanner.nextInt();
                if (!(choise>=0 && choise<= countOfOptions)) {
                    System.out.println("Введите число от 0 до " + countOfOptions);
                } else {
                    isRigthChoise = true;
                    switch (choise) {
                        case 0:{
                            exit = true;
                            break;
                        }
                        case 1: {
                            System.out.println("Вы выбрали добавление нового сотрудника.");
                            addNewEmployee();
                            break;
                        }
                        case 2: {
                            System.out.println("Вы выбрали обновление должности сотрудника");
                            scanner.nextLine();
                            System.out.println("Введите имя сотрудника:");
                            String name = scanner.nextLine();
                            System.out.println("Введите новую должность сотрудника");
                            String newPosition = scanner.nextLine();
                            Employee.positionUpdate(name,newPosition);
                            break;
                        }
                        case 3:{
                            System.out.println("Вы выбрали Простмотр информации о всех сотрудниках.");
                            System.out.println("*********************************");
                            System.out.println("Список всех сотрудников");
                            Employee.displayAllEmployees();
                            break;
                        }

                    }
                }


            }
        }

    }

    private static void manageFinance(){
        int countOfOptions = 3;
        boolean exit = false;
        while (!exit) {
            System.out.println("*********************************");
            System.out.println("Выберите действие:");
            System.out.println("0. Exit");
            System.out.println("1. Провести финансовую операцию");
            System.out.println("2. Показать все проведенные финансовые операции для проекта");
            System.out.println("3. Показать баланс всех проектов");
            System.out.println("**********************************");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();
            boolean isRigthChoise = false;
            while (!isRigthChoise) {
                int choise = scanner.nextInt();
                if (!(choise>=0 && choise<= countOfOptions)) {
                    System.out.println("Введите число от 0 до " + countOfOptions);
                } else {
                    isRigthChoise = true;
                    switch (choise) {
                        case 0:{
                            exit = true;
                            break;
                        }
                        case 1: {
                            System.out.println("Вы выбрали провести финансовую операцию.");
                            scanner.nextLine();
                            System.out.println("Введите имя проекта");
                            String name = scanner.nextLine();
                            System.out.println("Введите тип транзакции Доход/Расход:");
                            String typeStr = scanner.nextLine();
                            TypeOfTransaction type;
                            if(typeStr.equalsIgnoreCase("доход")){
                               type = TypeOfTransaction.INCOME;
                            }else{
                                type = TypeOfTransaction.EXPENSE;
                            }
                            System.out.println("Введите cумму транзакции (только положительные числа!)");
                            checkValueDouble();
                            double amount = scanner.nextDouble();
                            System.out.print("Введите дату транзакции в формате yyyy-MM-dd: ");
                            String dataStr = scanner.next();
                            LocalDate date = LocalDate.parse(dataStr);
                            System.out.println("Введите категорию расхода:");
                            String category = scanner.nextLine();

                            System.out.println("");
                            Project.makeTransaction(name,type,amount,date,category);
                            break;
                        }
                        case 2: {
                            System.out.println("Вы выбрали обновление должности сотрудника");
                            scanner.nextLine();
                            System.out.println("Введите имя сотрудника:");
                            String name = scanner.nextLine();
                            System.out.println("Введите новую должность сотрудника");
                            String newPosition = scanner.nextLine();
                            Employee.positionUpdate(name,newPosition);
                            break;
                        }
                        case 3:{
                            System.out.println("Вы выбрали Простмотр информации о всех сотрудниках.");
                            System.out.println("*********************************");
                            System.out.println("Список всех сотрудников");
                            Employee.displayAllEmployees();
                            break;
                        }

                    }
                }


            }
        }

    }
    private static void manageTasks(){

    }



    private static void addNewProject(){
        scanner.nextLine();
        System.out.print("Введите название проекта: ");
        String projectName = scanner.nextLine();

        System.out.print("Введите описание проекта: ");
        String projectDescription = scanner.nextLine();

        System.out.print("Введите бюджет проекта: ");
        checkValueDouble();
        Double projectBudget = scanner.nextDouble();


        Project newProject = new Project(projectName, projectDescription, projectBudget);
        Project.addNewProject(newProject);
    }
    private static void updateProjectInfo() {
        scanner.nextLine();
        System.out.print("Введите название проекта: ");
        String projectName = scanner.nextLine();
        System.out.println("Какую информцию Вы хотите обновить?");
        System.out.println("1. Название");
        System.out.println("2. Описание");
        System.out.println("3. Бюджет");
        System.out.println("4. Статус");
        System.out.print("Введите Ваш выбор: ");
        checkValueInt();
        boolean isRigthChoise = false;
        while (!isRigthChoise) {
            int choise = scanner.nextInt();
            if (!(choise >= 1 && choise <= 4)) {
                System.out.println("Введите число от 0 до " + 4);
            } else {
                isRigthChoise = true;
                switch (choise){
                    case 1:{
                        System.out.println("Обновляем название проекта");
                        scanner.nextLine();
                        System.out.println("Введите новое название проекта: ");
                        String newProjectName = scanner.nextLine();
                        Project.nameUpdate(projectName,newProjectName);
                        break;
                    }
                    case 2:{
                        System.out.println("Обновляем описание проекта");
                        scanner.nextLine();

                        System.out.println("Введите новое описание проекта");
                        String description = scanner.nextLine();
                        Project.descriptionUpdate(projectName,description);
                        break;
                    }
                    case 3:{
                        System.out.println("Обновляем бюджет проекта");
                        System.out.println("Введите новый бюджет проекта");
                        checkValueDouble();
                        double newBudget = scanner.nextDouble();
                        Project.budgetUpdate(projectName,newBudget);
                        break;
                    }
                    case 4:{
                        System.out.println("Обновляем статус проекта.");
                        System.out.println("Введите новый статус проекта: ACTIV/PASSIV");
                        String statusStr = scanner.next();
                        ProjectStatus projectStatus;
                        if(statusStr.equalsIgnoreCase("ACTIV")){
                            projectStatus =ProjectStatus.ACTIV;
                        }
                        else{
                            projectStatus = ProjectStatus.PASSIV;
                        }
                        Project.statusUpdate(projectName,projectStatus);
                        break;
                    }
                }
            }

        }
    }

    private static void addNewEmployee(){
        scanner.nextLine();
        System.out.print("Введите имя сотрудника: ");
        String name = scanner.nextLine();

        System.out.print("Введите должность сотрудника: ");
        String position = scanner.nextLine();

        System.out.print("Введите дату начала работы сотрудника в формате yyyy-MM-dd: ");
        String dataStr = scanner.next();
        LocalDate date = LocalDate.parse(dataStr);


        Employee newEmployee = new Employee(name, position, date);
        Employee.addNewEmployee(newEmployee);
    }


    private static void checkValueInt () {
            while (!scanner.hasNextInt()) {
                System.out.println("It must be a number! Enter again");
                scanner.next();
            }
        }
    private static void checkValueDouble () {
        while (!scanner.hasNextDouble()) {
            System.out.println("It must be a number! Enter again");
            scanner.next();
        }
    }


}
