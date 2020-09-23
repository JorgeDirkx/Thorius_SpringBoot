package com.jorgedirkx.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    private long id;
    private String name;
    private String employee;
    private String status;

    private Set<Bug> bugs= new HashSet<Bug>(0);

    public Project() {
    }

    public Project(long id, String name, String employee, String status, Set<Bug> bugs) {
        this.id = id;
        this.name = name;
        this.employee = employee;
        this.status = status;
        this.bugs = bugs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name ="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name ="employee")
    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Column(name ="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="project_bug", joinColumns = {@JoinColumn(name="id")}, inverseJoinColumns = {@JoinColumn(name ="bugId")})
    public Set<Bug> getBugs() {
        return this.bugs;
    }

    public void setBugs(Set<Bug> bugs) {
        this.bugs = bugs;
    }

    public boolean hasBug(Bug bug) {
        for (Bug projectBug : getBugs()) {
            if (projectBug.getBugId() == bug.getBugId()) {
                return true;
            }
        }
        return false;
    }
}


