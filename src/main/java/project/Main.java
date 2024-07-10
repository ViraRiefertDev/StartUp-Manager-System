package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Добро пожаловать в систему управления стартапом!");

        boolean exit = false;
        while (!exit) {
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

            int choise = scanner.nextInt();
            switch (choise) {
                case 0: {
                    exit = true;
                    Project.serializeProjects();
                    Employee.serializeEmployee();
                    System.out.println("До свидания!");
                    break;
                }
                case 1:
                    managerProjects();
                    break;
                case 2:
                    manageEmployee();
                    break;
                case 3:
                    manageFinance();
                    break;
                case 4:
                    manageTasks();
                    break;
                default:
                    System.out.println("Введите число от 0 до " + 4);
                    break;


            }

        }
    }

    //-------------------------------------------------
    private static void managerProjects() {
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

            int choise = scanner.nextInt();


            switch (choise) {
                case 0: {
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
                case 3: {
                    System.out.println("Вы выбрали обновление информации о проекте.");
                    updateProjectInfo();
                    break;
                }
                default:
                    System.out.println("Введите число от 0 до " + 3);
                    break;

            }
        }


    }

    private static void manageEmployee() {

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

            int choise = scanner.nextInt();

            switch (choise) {
                case 0: {
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
                    updatePosition();
                    break;
                }
                case 3: {
                    System.out.println("Вы выбрали Простмотр информации о всех сотрудниках.");
                    System.out.println("*********************************");
                    System.out.println("Список всех сотрудников");
                    Employee.displayAllEmployees();
                    break;
                }
                default: {
                    System.out.println("Введите число от 0 до " + 3);
                    break;
                }
            }
        }


    }

    private static void manageFinance() {
        boolean exit = false;
        while (!exit) {
            System.out.println("*********************************");
            System.out.println("Выберите действие:");
            System.out.println("0. Exit");
            System.out.println("1. Добавить доход");
            System.out.println("2. Добавить расход");
            System.out.println("3. Показать все проведенные финансовые операции для проекта");
            System.out.println("**********************************");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();

            int choise = scanner.nextInt();

            switch (choise) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    System.out.println("Вы выбрали добавление дохода.");
                    addIncomeTransaction();
                    break;
                }
                case 2: {
                    System.out.println("Вы выбрали добавление расхода.");
                    addExpenseTransaction();
                    break;

                }
                case 3: {
                    System.out.println("Вы выбрали показать все проведенные финансовые операции для проекта");
                    scanner.nextLine();
                    System.out.println("Введите имя проекта");
                    String projectName = scanner.nextLine();
                    Project.printAllTransactions(projectName);
                    break;
                }
                default: {
                    System.out.println("Введите число от 0 до " + 3);
                    break;
                }
            }
        }


    }

    private static void manageTasks() {
        boolean exit = false;
        while (!exit) {
            System.out.println("*********************************");
            System.out.println("Выберите действие:");
            System.out.println("0. Exit");
            System.out.println("1. Добавить новую задачу");
            System.out.println("2. Посмотреть список всех задач проекта");
            System.out.println("3. Посмотреть список всех задач сотрудника");
            System.out.println("**********************************");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();

            int choise = scanner.nextInt();
            switch (choise) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    System.out.println("Вы выбрали добавить новую задачу.");
                    addNewTaskInProject();

                    break;
                }
                case 2: {
                    System.out.println("Вы выбрали посмотреть список всех задач проекта");
                    showAllProjectTasks();

                    break;
                }
                case 3: {
                    System.out.println("Вы выбрали посмотреть список всех задач сотрудника");
                    showAllEmployeeTasks();
                    break;
                }
                default: {
                    System.out.println("Введите число от 0 до " + 3);
                    break;

                }
            }
        }
    }

    //-------------------------------------------------


    //------------------Методы manageProjects----------
    private static void addNewProject() {
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
        boolean exit = false;
        while (!exit) {
            System.out.println("Какую информцию Вы хотите обновить?");
            System.out.println("0. Exit");
            System.out.println("1. Название");
            System.out.println("2. Описание");
            System.out.println("3. Бюджет");
            System.out.println("4. Статус");
            System.out.print("Введите Ваш выбор: ");
            checkValueInt();
            int choise = scanner.nextInt();
            switch (choise) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    System.out.println("Обновляем название проекта");
                    scanner.nextLine();
                    System.out.println("Введите новое название проекта: ");
                    String newProjectName = scanner.nextLine();
                    Project.nameUpdate(projectName, newProjectName);
                    break;
                }
                case 2: {
                    System.out.println("Обновляем описание проекта");
                    scanner.nextLine();

                    System.out.println("Введите новое описание проекта");
                    String description = scanner.nextLine();
                    Project.descriptionUpdate(projectName, description);
                    break;
                }
                case 3: {
                    System.out.println("Обновляем бюджет проекта");
                    System.out.println("Введите новый бюджет проекта");
                    checkValueDouble();
                    double newBudget = scanner.nextDouble();
                    Project.budgetUpdate(projectName, newBudget);
                    break;
                }
                case 4: {
                    System.out.println("Обновляем статус проекта.");
                    System.out.println("Введите новый статус проекта: ACTIV/PASSIV");
                    String statusStr = scanner.next();
                    ProjectStatus projectStatus;
                    if (statusStr.equalsIgnoreCase("ACTIV")) {
                        projectStatus = ProjectStatus.ACTIV;
                    } else {
                        projectStatus = ProjectStatus.PASSIV;
                    }
                    Project.statusUpdate(projectName, projectStatus);
                    break;
                }
                default: {
                    System.out.println("Введите число от 0 до " + 3);
                    break;
                }
            }
        }

    }


    //-------------------------------------------------

    //------------------Методы manageEmployee----------
    private static void addNewEmployee() {
        scanner.nextLine();
        System.out.print("Введите имя сотрудника: ");
        String name = scanner.nextLine();

        System.out.print("Введите должность сотрудника: ");
        String position = scanner.nextLine();

        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("Введите дату начала работы сотрудника в формате yyyy-MM-dd: ");
                String dataStr = scanner.next();
                date = LocalDate.parse(dataStr);
            } catch (DateTimeParseException exception) {
                LOGGER.warn("Некорректный формат даты. Пожалуйста, используйте формат yyyy-MM-dd.");
            }
        }
        Employee newEmployee = new Employee(name, position, date);
        Employee.addNewEmployee(newEmployee);
    }

    private static void updatePosition() {
        scanner.nextLine();
        System.out.println("Введите имя сотрудника:");
        String name = scanner.nextLine();
        System.out.println("Введите новую должность сотрудника");
        String newPosition = scanner.nextLine();
        Employee.positionUpdate(name, newPosition);

    }

    //-------------------------------------------------

    //------------------Методы manageFinance----------

    private static void addIncomeTransaction() {
        scanner.nextLine();
        System.out.println("Введите имя проекта");
        String name = scanner.nextLine();
        System.out.println("Введите cумму дохода (только положительные числа!)");
        checkValueDouble();
        double amount = scanner.nextDouble();
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("Введите дату транзакции в формате yyyy-MM-dd: ");
                String dataStr = scanner.next();
                date = LocalDate.parse(dataStr);
            } catch (DateTimeParseException exception) {
                LOGGER.warn("Некорректный формат даты. Пожалуйста, используйте формат yyyy-MM-dd.");
            }
        }
        scanner.nextLine();
        System.out.println("Введите категорию дохода:");
        String category = scanner.nextLine();

        System.out.println("");
        Project.makeIncomeTransaction(name, amount, date, category);
    }

    private static void addExpenseTransaction() {
        scanner.nextLine();
        System.out.println("Введите имя проекта");
        String name = scanner.nextLine();
        System.out.println("Введите cумму расхода (только положительные числа!)");
        checkValueDouble();
        double amount = scanner.nextDouble();
        System.out.print("Введите дату транзакции в формате yyyy-MM-dd: ");
        String dataStr = scanner.next();
        LocalDate date = LocalDate.parse(dataStr);
        scanner.nextLine();
        System.out.println("Введите категорию расхода:");
        String category = scanner.nextLine();

        System.out.println("");
        Project.makeExpenseTransaction(name, amount, date, category);
    }

    //-------------------------------------------------

    //------------------Методы manageTasks-------------

    private static void addNewTaskInProject() {
        scanner.nextLine();
        System.out.println("Введите имя проекта");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        System.out.print("Введите дату дедлайна задачи в формате yyyy-MM-dd: ");
        String dataStr = scanner.next();
        LocalDate date = LocalDate.parse(dataStr);
        scanner.nextLine();
        System.out.println("Введите имя ответсвенного сотрудника");
        String employeeName = scanner.nextLine();


        Project.addNewTaskInProject(name, description, date, employeeName);
    }

    private static void showAllProjectTasks() {
        scanner.nextLine();
        System.out.println("Введите название проекта");
        String projectName = scanner.nextLine();
        System.out.println("*********************************");
        System.out.println("Список всех задач проекта: " + projectName);
        Project.printAllTasks(projectName);
    }

    private static void showAllEmployeeTasks() {
        scanner.nextLine();
        System.out.println("Введите имя сотрудника");
        String employeeName = scanner.nextLine();
        System.out.println("*********************************");
        System.out.println("Список всех задач сотрудника: " + employeeName);
        Employee.printAllTasks(employeeName);
    }
    //-------------------------------------------------

    private static void checkValueInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите пожалуйста целое число");
            scanner.next();
        }
    }

    private static void checkValueDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Введите пожалуйста число!");
            scanner.next();
        }
    }


}
