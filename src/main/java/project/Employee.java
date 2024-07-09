package project;

import java.time.LocalDate;
import java.util.List;

public class Employee {
    private String name;
    private String position;//должность сотрудника
    private LocalDate startDate; //дата начала работы
    private List<Project> projectList;
    private List<Task>tasks;
}
