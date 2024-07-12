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
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Setter
    private String name;
    @Setter
    private String position;//должность сотрудника
    @Setter
    private LocalDate startDate; //дата начала работы
    private List<Project> projectList;
    private List<Task> tasks;

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

}
