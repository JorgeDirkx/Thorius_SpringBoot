package com.jorgedirkx.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bug {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long bugId;

    @Column(name="bugissue")
    private String issue;

    @ManyToMany(mappedBy = "bugs")
    private Set<Project> projects;

    public Bug() {
    }

    public Bug(String issue) {
        this.issue = issue;
    }

    public long getBugId() {
        return bugId;
    }

    public String getIssue() {
        return issue;
    }

    public void setBugId(long bugId) {
        this.bugId = bugId;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
