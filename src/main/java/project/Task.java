package project;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private int personalId;
    private String description;
    private LocalDate deadline;
    private TaskStatus status;
    private Employee employee;
    private Project project;

}
