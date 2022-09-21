package com.greymatter.earnjoy.model;

public class Job_list {

    String id,job_title,email,salary,interview_date,interview_time,company_address,company_name,emp_qualification,emp_experience,link,venue,more_details,language;

    public Job_list(String id, String job_title, String email, String salary, String interview_date, String interview_time, String company_address, String company_name, String emp_qualification, String emp_experience, String link, String venue, String more_details, String language) {
        this.id = id;
        this.job_title = job_title;
        this.email = email;
        this.salary = salary;
        this.interview_date = interview_date;
        this.interview_time = interview_time;
        this.company_address = company_address;
        this.company_name = company_name;
        this.emp_qualification = emp_qualification;
        this.emp_experience = emp_experience;
        this.link = link;
        this.venue = venue;
        this.more_details = more_details;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(String interview_date) {
        this.interview_date = interview_date;
    }

    public String getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(String interview_time) {
        this.interview_time = interview_time;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmp_qualification() {
        return emp_qualification;
    }

    public void setEmp_qualification(String emp_qualification) {
        this.emp_qualification = emp_qualification;
    }

    public String getEmp_experience() {
        return emp_experience;
    }

    public void setEmp_experience(String emp_experience) {
        this.emp_experience = emp_experience;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getMore_details() {
        return more_details;
    }

    public void setMore_details(String more_details) {
        this.more_details = more_details;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
