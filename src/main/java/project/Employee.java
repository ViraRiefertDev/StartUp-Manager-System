package project;

import lombok.Getter;
import lombok.Setter;
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
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    private static List<Employee> allEmployees;
    @Setter
    private String name;
    @Setter
    private String position;//должность сотрудника
    @Setter
    private LocalDate startDate; //дата начала работы
    private List<Project> projectList;
    private List<Task> tasks;

    //Инициализация статичного поля allEmployees
    static {
        String fileName = "employee.ser";
        File file = new File(fileName);
        if (file.exists()) {
            deserializeEmployee();
        } else {
            allEmployees = new ArrayList<>();
        }
    }

    public Employee(String name, String position, LocalDate startDate) {
        this.name = name;
        this.position = position;
        this.startDate = startDate;
        projectList = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "" + this.name + " - Позиция: " + position + ", Дата начала работы: "
                + startDate;
    }

    //статический метод добавления нового сотрудника
    public static void addNewEmployee(Employee newEmployee) {
        if (newEmployee != null) {
            //проверка существует ли такой сотрудник в списке
            boolean isEmployeeExist = isEmployeeExist(newEmployee);
            if (!isEmployeeExist) {
                allEmployees.add(newEmployee);
                LOGGER.info("Employee with name " + newEmployee.getName() + " was successfully added into the list");
            } else {
                LOGGER.error("Employee with name " + newEmployee.getName() + " is already exist!");
            }
        } else {
            LOGGER.error("Project is null");
        }
    }
    //--------------------------------------------------------------------------------------------------


    //метод обновления должности сотрудника

    public static void positionUpdate(String employeeName, String newPosition) {
        //проверка существует ли такой проект в списке
        boolean isEmployeeFound = isEmployeeExist(employeeName);
        if (isEmployeeFound) {
            allEmployees.stream().map(employee -> {
                if (employee.getName().equals(employeeName)) {
                    employee.setPosition(newPosition);
                }
                return employee;
            }).collect(Collectors.toList());
            LOGGER.info("Employee position of " + employeeName + " was successfully updated: " + newPosition);
        } else {
            LOGGER.warn("Employee with such name: " + employeeName + "was not found!");
        }
    }

    //метод просмотра информации о сотрудниках
    public static void displayAllEmployees() {
        if (allEmployees.isEmpty()) {
            LOGGER.warn("Список сотрудников пуст!");
        } else {
            for (Employee employee : allEmployees) {
                System.out.println("----------Сотрудник: " + (allEmployees.indexOf(employee) + 1) + "-----------");
                System.out.println(employee);

            }
        }
    }


    //------------------------------------------------------------------------------------
    //проверка существует ли такой сотрудник в списке
    public static boolean isEmployeeExist(Employee employeeToCheck) {
        if (!allEmployees.isEmpty()) {
            return allEmployees.stream()
                    .anyMatch(employee -> employee.getName().equals(employeeToCheck.getName()));
        } else {
            return false;
        }
    }

    public static Employee foundEmployeeByName(String employeeName){
        return allEmployees.stream().filter(e -> e.getName().equals(employeeName)).findFirst().orElse(null);
    }

    public static boolean isEmployeeExist(String employeeName) {
        if (!allEmployees.isEmpty()) {
            return allEmployees.stream()
                    .anyMatch(project -> project.getName().equals(employeeName));
        } else {
            return false;
        }
    }
    //-------------------------------------------------------------------------------------

    //Метод сериализации списка сотрудников
    public static void serializeEmployee() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            oos.writeObject(allEmployees);
            LOGGER.info("Список проектов был сериализован в файл employee.ser");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    //Метод десериализации списка объектов
    public static void deserializeEmployee() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employee.ser"))) {
            allEmployees = (List<Employee>) ois.readObject();
            LOGGER.info("Файл employee.ser был успешно десериализован, и сотрудники сохранены в список сотрудников!");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    //метод добавления задачи в список задач
    public static boolean addnewTask(Employee employee, Task task) {

        employee.tasks.add(task);
        LOGGER.info("Задача " + task.getDescription() + " была успешно добавлена в список задач сотрудника " + employee.getName());
        return true;

    }

    //метод вывода списка задач сотрудника на экран
    public static void printAllTasks(String employeeName){
        if(isEmployeeExist(employeeName)){
            Employee employee = foundEmployeeByName(employeeName);
            System.out.println(employee.tasks);
        }

    }

}



