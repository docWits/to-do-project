package com.romanyuta.todoproject.api;

import com.romanyuta.todoproject.model.Project;
import com.romanyuta.todoproject.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/project")
@Tag(name = "Проекты", description = "Взаимодействие с проектами")
public class ProjectController {

    private  final ProjectService projectService;

    @Autowired
    public  ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @Operation(
            summary = "Список всех проектов",
            description = "Позволяет посмотреть список всех проектов"
    )
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Project> getProject(){
        return projectService.getProjects();
    }

    @Operation(
            summary = "Новый проект",
            description = "Позволяет добавить новый проект"
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus registerNewProject(@RequestBody Project project){
        projectService.addNewProject(project);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Удаление проект",
            description = "Позволяет удалить проект по id"
    )
    @DeleteMapping(path = "{projectId}")
    public HttpStatus deleteRelease(@PathVariable("projectId") @Parameter(description = "Идентификатор") Long projectId){
        projectService.deleteProject(projectId);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Изменение проекта",
            description = "Позволяет обновить информацию о проекте"
    )
    @PutMapping(path = "{projectId}", produces = APPLICATION_JSON_VALUE)
    public HttpStatus updateRelease(
            @PathVariable("projectId") @Parameter(description = "Идентификатор") Long projectId,
            @RequestParam(required = false) @Parameter(description = "Новый id релиза") Long id_release,
            @RequestParam(required = false) @Parameter(description = "Новый заказчик") String customer,
            @RequestParam(required = false) @Parameter(description = "Новое название проекта") String title,
            @RequestParam(required = false) @Parameter(description = "Новое время оформления договора") LocalDate contract_date

    ){
        projectService.updateProject(projectId,id_release,customer,title,contract_date);
        return HttpStatus.OK;
    }
}
