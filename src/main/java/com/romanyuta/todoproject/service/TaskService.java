package com.romanyuta.todoproject.service;

import com.romanyuta.todoproject.model.Project;
import com.romanyuta.todoproject.model.Release;
import com.romanyuta.todoproject.model.Task;
import com.romanyuta.todoproject.model.Users;
import com.romanyuta.todoproject.repository.TaskRepository;
import com.romanyuta.todoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public ReleaseService releaseService;

    @Autowired
    public ProjectService projectService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.getById(id);
    }

    public void addNewTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalStateException("task with id=" + taskId + " does not exist");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public void updateTask(Long Id, Long id_project,Long id_release, String title, String status, Long id_author, Long id_worker) {
        Task task = taskRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException(
                        "task with id=" + Id + " does not exist"
                ));

        if(id_project != null && !Objects.equals(task.getProject().getId(),id_project)){
            Project project = projectService.getProjectById(id_project);
            task.setProject(project);
        }
        if(id_release != null && !Objects.equals(task.getRelease().getId(),id_release)){
            Release release = releaseService.getReleaseById(id_release);
            task.setRelease(release);
        }
        if(title != null && title.length()>0 && !Objects.equals(task.getTitle(),title)){
            task.setTitle(title);
        }
        if(status != null && status.length()>0 && !Objects.equals(task.getStatus(),status)){
            task.setStatus(status);
        }

        if(id_author != null && !Objects.equals(task.getAuthor().getId(),id_author)){
            Users author = userRepository.getById(id_author);
            task.setAuthor(author);
        }

        if(id_worker != null && !Objects.equals(task.getWorker().getId(),id_worker)){
            Users worker = userRepository.getById(id_worker);
            task.setWorker(worker);
        }
    }

}
