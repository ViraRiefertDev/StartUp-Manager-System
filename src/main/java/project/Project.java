package project;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Project.class);

    private static List<Project> allProjects;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private double budget;
    private List<Task> tasks;
    private List<Employee> employees;
    @Setter
    private ProjectStatus status;

    private Finance finance;

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

    public Project(String name, String description, double budget) {
        this.name = name;
        this.description = description;
        this.budget = budget;

        this.tasks = new ArrayList<>(20);
        this.employees = new ArrayList<>(5);
        this.status = ProjectStatus.ACTIV;
        finance = new Finance(budget);
    }

    @Override
    public String toString() {
        return "" + this.name + " - Бюджет: " + budget + ", Статус: "
                + status + "\nОписание проекта: \n" + description;
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

    //метод проведения финансовой операции для проекта
    public static void makeTransaction(String projectName, TypeOfTransaction type, double amount, LocalDate date, String category) {
        boolean isProjectFount = isProjectExist(projectName);
        if (isProjectFount) {
            allProjects.stream().map(project -> {
                if (project.getName().equals(projectName)) {
                    project.getFinance().addNewTransaction(type, amount, date, category);
                }
                return project;
            });
        }
    }


    //------------------------------------------------------------------------------------
    //проверка существует ли такой проект в списке
    private static boolean isProjectExist(Project projectToCheck) {
        if (!allProjects.isEmpty()) {
            return allProjects.stream()
                    .anyMatch(project -> project.getName().equals(projectToCheck.getName()));
        } else {
            return false;
        }
    }

    private static boolean isProjectExist(String projectName) {
        if (!allProjects.isEmpty()) {
            return allProjects.stream()
                    .anyMatch(project -> project.getName().equals(projectName));
        } else {
            return false;
        }
    }
    //-------------------------------------------------------------------------------------

    //Метод сериализации списка проектов
    public static void serializeProjects() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects.ser"))) {
            oos.writeObject(allProjects);
            LOGGER.info("Список проектов был сериализован в файл rojects.ser");
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
}
