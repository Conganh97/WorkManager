package Model;

import java.util.Date;

public class Task extends GeneralInformation {

    private final String taskCode;
    private final String nameTask;
    private String description;
    private int TimeEstimate;
    private User worker;
    private String status;

    public Task(Date dateCreated, String creator,String taskCode, String nameTask, String description, int timeEstimate, User worker) {
        super(dateCreated, creator);
        this.taskCode =taskCode;
        this.nameTask = nameTask;
        this.description = description;
        TimeEstimate = timeEstimate;
        this.worker = worker;
        this.status = "To do";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public String getDescription() {
        return description;
    }

    public int getTimeEstimate() {
        return TimeEstimate;
    }

    public void setTimeEstimate(int timeEstimate) {
        TimeEstimate = timeEstimate;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
