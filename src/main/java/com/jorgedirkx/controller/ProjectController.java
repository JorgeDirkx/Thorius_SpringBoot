package com.jorgedirkx.controller;

import com.jorgedirkx.entities.Bug;
import com.jorgedirkx.entities.Project;
import com.jorgedirkx.repositories.BugRepository;
import com.jorgedirkx.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private BugRepository bugRepository;

    @RequestMapping("/projects")
    public String index(Model model){
        List<Project>projects =(List<Project>) repository.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @RequestMapping(value = "add")
    public String addProject(Model model){
        model.addAttribute("project", new Project());
        return "addProject";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editProject(@PathVariable("id") Long projectId, Model model){
        model.addAttribute("project", repository.findById(projectId));
        return "editProject";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Project project){
        repository.save(project);
        return "redirect:/projects";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable("id") Long projectId, Model model) {
        repository.deleteById(projectId);
        return "redirect:/projects";
    }

    @RequestMapping(value = "addProjectBug/{id}", method = RequestMethod.GET)
    public String addBug(@PathVariable("id") Long projectId, Model model){

        model.addAttribute("bugs", bugRepository.findAll());
        model.addAttribute("project", repository.findById(projectId).get());
        return "addProjectBug";
    }


    @RequestMapping(value="/project/{id}/bugs", method=RequestMethod.GET)
    public String projectsAddBug(@RequestParam(value="action", required=true) String action, @PathVariable Long id, @RequestParam Long bugId, Model model) {
        Optional<Bug> bug = bugRepository.findById(bugId);
        Optional<Project> project = repository.findById(id);

        if (project.isPresent() && action.equalsIgnoreCase("save")) {
            if (!project.get().hasBug(bug.get())) {
                project.get().getBugs().add(bug.get());
            }
            repository.save(project.get());
            model.addAttribute("project", bugRepository.findById(id));
            model.addAttribute("bugs", bugRepository.findAll());
            return "redirect:/projects";
        }

        model.addAttribute("developers", repository.findAll());
        return "redirect:/projects";

    }

    @RequestMapping(value = "getprojects", method = RequestMethod.GET)
    public @ResponseBody List<Project> getStudents() {
        return (List<Project>)repository.findAll();
    }


}
