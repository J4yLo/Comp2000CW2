package com.example.comp2000cw2;

public class Projects {
    String projectName, projectUsers, projectDescription, projectCode, projectDate;
    int imgID;

    public Projects(String projectName, String projectUsers, String projectDescription, String projectCode, String projectDate, int imgID) {
        this.projectName = projectName;
        this.projectUsers = projectUsers;
        this.projectDescription = projectDescription;
        this.imgID = imgID;
        this.projectCode = projectCode;
        this.projectDate = projectDate;
    }
}
