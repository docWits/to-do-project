package com.romanyuta.todoproject.service;

import com.romanyuta.todoproject.model.Project;
import com.romanyuta.todoproject.model.Release;
import com.romanyuta.todoproject.model.Users;
import com.romanyuta.todoproject.repository.ProjectRepository;
import com.romanyuta.todoproject.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ReleaseService releaseService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        return projectRepository.getById(id);
    }

    public void addNewProject(Project project){
        projectRepository.save(project);
    }

    public void deleteProject(Long Id) {
        if (!projectRepository.existsById(Id)) {
            throw new IllegalStateException("project with id=" + Id + " does not exist");
        }
        projectRepository.deleteById(Id);
    }

    @Transactional
    public void updateProject(Long Id, Long id_release, String customer, String title, LocalDate contract_date) {
        Project project = projectRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException(
                        "project with id=" + Id + " does not exist"
                ));
        if(id_release != null && !Objects.equals(project.getRelease().getId(),id_release)){
            Release release = releaseService.getReleaseById(id_release);
            project.setRelease(release);
        }
        if(customer != null && customer.length()>0 && !Objects.equals(project.getCustomer(),customer)){
            project.setCustomer(customer);
        }
        if(title != null && title.length()>0 && !Objects.equals(project.getTitle(),title)){
            project.setTitle(title);
        }
        if(contract_date != null && !Objects.equals(project.getContract_date(),contract_date)){
            project.setContract_date(contract_date);
        }
    }
}
