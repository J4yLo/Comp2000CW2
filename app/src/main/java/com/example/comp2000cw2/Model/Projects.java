package com.example.comp2000cw2.Model;

public class Projects {
    String ProjectName, ProjectUsers, ProjectDescription;
    int ImgID;

    public Projects(String projectName, String projectUsers, String projectDescription, int imgID) {
        this.ProjectName = projectName;
        this.ProjectUsers = projectUsers;
        this.ProjectDescription = projectDescription;
        this.ImgID = imgID;
    }
}
