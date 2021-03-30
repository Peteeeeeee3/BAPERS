package Report;

import Job.Job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice extends Report {
    private String name, company, address, date, completedDate;
    private int accountNo, jobNo;
    private long phone;
    private Job job;
    float discount;

    public String getDate() {
        return date;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public int getAccountNo() {
        return accountNo;
    }
    public int getJobNo() {
        return jobNo;
    }
    public long getPhone() {
        return phone;
    }
    public String getCompany() {
        return company;
    }
    public String getCompletedDate() {
        return completedDate;
    }
    public Job getJob() {
        return job;
    }
    public float getDiscount() {
        return discount;
    }

    private void setDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMdd");
        String text = localDate.format(formatter);
        this.date = text;
    }

    private void setCompletedDate(int completedDate) {
        String year = Integer.toString(completedDate).substring(0, 4);
        String month = Integer.toString(completedDate).substring(4, 6);
        String day = Integer.toString(completedDate).substring(6, 8);
        this.completedDate = year + "/" + month + "/" + day;
    }

    public Invoice(String name, String company, String address, int accountNo, int jobNo, long phone, int completedDate, Job job, float discount) {
        this.name = name;
        this.company = company;
        this.address = address;
        this.accountNo = accountNo;
        this.jobNo = jobNo;
        this.phone = phone;
        this.job = job;
        this.discount = discount;
        setDate();
        setCompletedDate(completedDate);
    }
}
