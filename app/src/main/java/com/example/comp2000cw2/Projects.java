package com.example.comp2000cw2;

public class Projects {
    String projUserID, projectName, projectUsers, projectDescription, projectCode, projectDate ,imgBytes, poster, thumbnail;

    public Projects(String projUserID, String projectName, String projectUsers, String projectDescription, String projectCode, String projectDate, String imgBytes, String poster, String thumbnail ) {
        this.projUserID = projUserID;
        this.projectName = projectName;
        this.projectUsers = projectUsers;
        this.projectDescription = projectDescription;
        this.projectCode = projectCode;
        this.projectDate = projectDate;
        this.imgBytes = imgBytes;
        this.poster = poster;
        this.thumbnail = thumbnail;
    }
}
