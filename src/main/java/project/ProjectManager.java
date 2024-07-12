package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManager.class);

    private static List<Project> allProjects;

    //Инициализация статичного поля allProjects
    static {
        String fileName = "projects.ser";
        File file = new File(fileName);
        if (file.exists()) {
            deserializeProjects();
        } else {
            allProjects = new ArrayList<>();
        }
    }

    //------------------------------------------------------------------------------------
    //Метод сериализации списка проектов
    public static void serializeProjects() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects.ser"))) {
            oos.writeObject(allProjects);
            LOGGER.info("Список проектов был сериализован в файл projects.ser");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    //Метод десериализации списка объектов
    public static void deserializeProjects() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("projects.ser"))) {
            allProjects = (List<Project>) ois.readObject();
            LOGGER.info("Файл projects.ser был успешно десериализован, и проекты сохранены в список проектов!");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    //метод просмотра информации о проектах
    public static void displayAllProjects() {
        if (allProjects.isEmpty()) {
            LOGGER.warn("Список проектов пуст!");
        } else {
            for (Project project : allProjects) {
                System.out.println("----------Проект: " + (allProjects.indexOf(project) + 1) + "-----------");
                System.out.println(project);

            }
        }
    }

    // статический метод добавление нового проекта
    public static void addNewProject(Project newProject) {
        if (newProject != null) {
            //проверка существует ли такой проект в списке
            boolean isProjectExists = isProjectExist(newProject);
            if (!isProjectExists) {
                allProjects.add(newProject);
                LOGGER.info("Project with name " + newProject.getName() + " was successfully added into the list");
            } else {
                LOGGER.error("Project with name " + newProject.getName() + " is already exist!");
            }
        } else {
            LOGGER.error("Project is null");
        }
    }

    //--------------------------------------------------------------------------------------------------
    //методы обновления информации о проектах

    //метод измениния имени проекта
    public static void nameUpdate(String oldProjectName, String newProjectName) {
        //проверка существует ли такой проект в списке
        boolean isProjectFound = isProjectExist(oldProjectName);
        if (isProjectFound) {
            allProjects.stream().map(project -> {
                if (project.getName().equals(oldProjectName)) {
                    project.setName(newProjectName);
                }
                return project;
            }).collect(Collectors.toList());
            LOGGER.info("Project name " + newProjectName + " was successfully updated");
        } else {
            LOGGER.warn("Project with such name: " + oldProjectName + "was not found!");
        }
    }

    //метод изменения описания проекта
    public static void descriptionUpdate(String projectName, String newDescription) {
        //проверка существует ли такой проект в списке
        boolean isProjectFound = isProjectExist(projectName);
        if (isProjectFound) {
            allProjects.stream().map(project -> {
                if (project.getName().equals(projectName)) {
                    project.setDescription(newDescription);
                }
                return project;
            }).collect(Collectors.toList());
            LOGGER.info("Description of project name " + projectName + " was successfully updated");
        } else {
            LOGGER.warn("Project with such name: " + projectName + "was not found!");
        }
    }

    //метод изменения бюджета проекта
    public static void budgetUpdate(String projectName, double newBudget) {
        //проверка существует ли такой проект в списке
        boolean isProjectFound = isProjectExist(projectName);
        if (isProjectFound) {
            allProjects.stream().map(project -> {
                if (project.getName().equals(projectName)) {
                    project.setBudget(newBudget);
                }
                return project;
            }).collect(Collectors.toList());
            LOGGER.info("Budget of project name " + projectName + " was successfully updated: " + newBudget);
        } else {
            LOGGER.warn("Project with such name: " + projectName + "was not found!");
        }
    }

    //метод изменения статуса проекта
    public static void statusUpdate(String projectName, ProjectStatus newStatus) {
        //проверка существует ли такой проект в списке
        boolean isProjectFound = isProjectExist(projectName);
        if (isProjectFound) {
            allProjects.stream().map(project -> {
                if (project.getName().equals(projectName)) {
                    project.setStatus(newStatus);
                }
                return project;
            }).collect(Collectors.toList());
            LOGGER.info("Status of project name " + projectName + " was successfully updated: " + newStatus);
        } else {
            LOGGER.warn("Project with such name: " + projectName + "was not found!");
        }
    }


    //------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    //Метод добавления новой задачи в проект
    public static void addNewTaskInProject(String projectName, String description, LocalDate deadline, String employeeName) {
        if (isProjectExist(projectName)) {
            if (EmployeeManager.isEmployeeExist(employeeName)) {
                Project project = foundProjectByName(projectName);
                Employee employee = EmployeeManager.foundEmployeeByName(employeeName);
                Task task = new Task(description, deadline, employee);
                task.setProject(project);
                project.getTasks().add(task);
                EmployeeManager.addnewTask(employee, task);
                LOGGER.info("Задача " + task.getDescription() + " была успешно добавлена в проект " + project.getName());
            } else {
                LOGGER.error("Ошибка добавления задачи в проект. Cотрудник с именем" + employeeName + " не был найден!");
            }
        } else {
            LOGGER.error("Ошибка добавления задачи в проект. Проект с именем" + projectName + " не был найден!");
        }

    }

    //метод вывода списка задач проекта на экран
    public static void printAllTasks(String projectName) {
        if (isProjectExist(projectName)) {
            Project project = foundProjectByName(projectName);
            if (!project.getTasks().isEmpty()) {
                System.out.println(project.getTasks());
            } else {
                System.out.println("В проект " + projectName + " еще не было добавлено ни одной задачи!");
            }
        } else {
            LOGGER.warn("Проекта с именем " + projectName + " не найдено");
        }

    }

    //Метод вывода на экран финансовой статистики по всем проектам
    public static void displayFinanceStatistics() {
        for (Project project : allProjects) {
            double totalIncome = project.getFinance().getTotalIncome();
            double totalExpense = project.getFinance().getTotalExpense();
            System.out.println("Имя проекта: " + project.getName());
            System.out.println("Актуальный бюджет проекта: " + project.getBudget());
            System.out.println("Общая сумма доходов: " + totalIncome);
            System.out.println("Общая сумма расходов: " + totalExpense);
            System.out.println("-----------------------------------");
        }
    }
    //методы изменения статуса задачи
    public static void updateTaskStatusInProject(String projectName, String description, TaskStatus newStatus) {
        Project project = foundProjectByName(projectName);
        if (project != null) {
            project.updateTaskStatusByDescription(description, newStatus);
        } else {
            LOGGER.error("Проект с именем " + projectName + " не найден!");
        }
    }
    //------------------------------------------------------------------------------------



    //метод проверки всех задач на своевременное выполнение
    public static void checkDeadlines(){
        List<Task> allTasks = new ArrayList<>();
        for (Project project : allProjects) {
            List<Task> projectTasks =  project.getTasks();
            allTasks.addAll(projectTasks);
        }
        //фильтрация списка задач, чтоб туда входили задачи только с статусом NEU и IN_PROCESS
        //Упорядочивание элементов по датам
        List<Task> filteredAndSortedTaks = allTasks.stream()
                .filter(task->task.getStatus()==TaskStatus.NEW||task.getStatus()==TaskStatus.IN_PROGRESS)
                .sorted(Comparator.comparing(Task::getDeadline))
                .collect(Collectors.toList());

        for (Task task : filteredAndSortedTaks) {
            if(task.getDeadline().isBefore(LocalDate.now())){
                System.out.print("\uD83D\uDE21");
            }else if(task.getDeadline().isEqual(LocalDate.now())){
                System.out.print("\uD83D\uDE2C");
            }else {
                System.out.print("\uD83D\uDE07");
            }
            formattedPrintTask(task);
        }
    }

    private static void formattedPrintTask(Task task){
        System.out.println(task.getDeadline() + " " + task.getEmployee().getName() + " " +task.getStatus() +"\nПроект: "+ task.getProject().getName() + "\n" + task.getDescription());
        System.out.println("--------");
    }

    //------------------------------------------------------------------------------------
    //метод добавления доходной транзакции
    public static void makeIncomeTransaction(String projectName, double amount, LocalDate date, String category) {
        TypeOfTransaction type = TypeOfTransaction.INCOME;
        makeTransaction(projectName, type, amount, date, category);
    }

    public static void makeExpenseTransaction(String projectName, double amount, LocalDate date, String category) {
        TypeOfTransaction type = TypeOfTransaction.EXPENSE;
        makeTransaction(projectName, type, amount, date, category);
    }

    //метод проведения финансовой операции для проекта
    private static void makeTransaction(String projectName, TypeOfTransaction type, double amount, LocalDate date, String category) {
        boolean isProjectFount = isProjectExist(projectName);
        if (isProjectFount) {
            Project project = foundProjectByName(projectName);
            project.addNewTransaction(project, type, amount, date, category);
            project.setBudget(project.getFinance().getBalance());
        }
    }

    private void addNewTransaction(Project project, TypeOfTransaction type, double amount, LocalDate date, String category) {
        project.getFinance().addNewTransaction(type, amount, date, category);
    }


    //метод печать всех транзакций проекта
    public static void printAllTransactions(String projectName) {
        Project project = foundProjectByName(projectName);
        project.getFinance().printAllTransaction();
    }



    //------------------------------------------------------------------------------------
    //проверка существует ли такой проект в списке
    public static boolean isProjectExist(Project projectToCheck) {
        if (!allProjects.isEmpty()) {
            return allProjects.stream()
                    .anyMatch(project -> project.getName().equals(projectToCheck.getName()));
        } else {
            return false;
        }
    }

    public static Project foundProjectByName(String projectName) {
        return allProjects.stream().filter(p -> p.getName().equals(projectName)).findFirst().orElse(null);
    }

    public static boolean isProjectExist(String projectName) {
        if (!allProjects.isEmpty()) {
            return allProjects.stream()
                    .anyMatch(project -> project.getName().equals(projectName));
        } else {
            return false;
        }
    }
    //-------------------------------------------------------------------------------------
}
