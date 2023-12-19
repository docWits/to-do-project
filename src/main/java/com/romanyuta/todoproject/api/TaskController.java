package com.romanyuta.todoproject.api;

import com.romanyuta.todoproject.model.Task;
import com.romanyuta.todoproject.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Задачи", description = "Взаимодействие с задачами")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){ this.taskService = taskService;}

    @Operation(
            summary = "Список всех задач проекта",
            description = "Позволяет посмотреть список всех задач"
    )
    @ExceptionHandler({})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @Operation(
            summary = "Новая задача",
            description = "Позволяет добавить новую задачу"
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus registerNewTask(@RequestBody Task task){
        taskService.addNewTask(task);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Удаление задачи",
            description = "Позволяет удалить задачу по id"
    )
    @DeleteMapping(path = "{taskId}")
    public HttpStatus deleteTask(@PathVariable("taskId") @Parameter(description = "Идентификатор") Long taskId){
        taskService.deleteTask(taskId);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Изменение задачи",
            description = "Позволяет обновить информацию о задаче"
    )
    @PutMapping(path = "{taskId}", produces = APPLICATION_JSON_VALUE)
    public HttpStatus updateTask(
            @PathVariable("taskId") @Parameter(description = "Идентификатор") Long taskId,
            @RequestParam(required = false) @Parameter(description = "Новый id проекта") Long id_project,
            @RequestParam(required = false) @Parameter(description = "Новый id релиза") Long id_release,
            @RequestParam(required = false) @Parameter(description = "Новое название задачи") String title,
            @RequestParam(required = false) @Parameter(description = "Новый статус") String status,
            @RequestParam(required = false) @Parameter(description = "Новый id автора") Long id_author,
            @RequestParam(required = false) @Parameter(description = "Новый id исполнителя") Long id_worker

    ){
        taskService.updateTask(taskId,id_project,id_release,title,status,id_author,id_worker);
        return HttpStatus.OK;
    }


}
