package project;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Project.class);

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

    //метод возвращает копию листа задач
    public List<Task> getTasks(){
        return new ArrayList<>(tasks);
    }

    //метод изменения статуса задачи
    public void updateTaskStatusByDescription(String description, TaskStatus newStatus) {
        boolean isFound = false;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                isFound = true;
                task.updateTaskStatus(newStatus);
                LOGGER.info("статус задачи " + description + " был успешно обновлен! Новый статус: " + newStatus);
            }
        }
        if(!isFound){
            LOGGER.error("Задачи с описанием :\n" + description + "\n не найдено");
        }
    }

    public void addNewTransaction(Project project, TypeOfTransaction type, double amount, LocalDate date, String category) {
        project.finance.addNewTransaction(type, amount, date, category);
    }
}
