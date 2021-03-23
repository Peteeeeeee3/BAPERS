package Report;

public class ReportTask {
    private int job, task, startTime, timeTaken;
    private float price;
    private String department, completedBy;

    public float getPrice() {
        return price;
    }
    public int getJob() {
        return job;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getTask() {
        return task;
    }
    public int getTimeTaken() {
        return timeTaken;
    }
    public String getCompletedBy() {
        return completedBy;
    }
    public String getDepartment() {
        return department;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setJob(int job) {
        this.job = job;
    }
    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public void setTask(int task) {
        this.task = task;
    }
    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public ReportTask(int jobID, float price, int taskID, String location, int startTime, int duration, String name) {
        this.job = jobID;
        this.price = price;
        this.task = taskID;
        this.department = location;
        this.startTime = startTime;
        this.timeTaken = duration;
        this.completedBy = name;
    }
}