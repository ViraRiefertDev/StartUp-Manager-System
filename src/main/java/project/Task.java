package project;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Setter
    private String description;
    @Setter
    private LocalDate deadline;
    @Setter
    private TaskStatus status;
    @Setter
    private Employee employee;
    @Setter
    private Project project;

    public Task(String description, LocalDate deadline, Employee employee) {
        this.description = description;
        this.deadline = deadline;
        this.employee = employee;
        this.status = TaskStatus.NEW;
    }

    @Override
    public String toString() {
        return "****************************\n" + "Проект: " + project.getName() + ". \nОписание задачи: " + this.description + " - Дедлайн: " + deadline + ", \nСтатус: "
                + status + " Имя исполнителя: " + employee.getName() + "\n****************************";
    }

    //метод изменения статуса задачи
    public void updateTaskStatus(TaskStatus newStatus){
        this.status = newStatus;
    }

}
