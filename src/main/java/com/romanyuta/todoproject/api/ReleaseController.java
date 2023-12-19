package com.romanyuta.todoproject.api;

import com.romanyuta.todoproject.model.Release;
import com.romanyuta.todoproject.service.ReleaseService;
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
@RequestMapping("/api/release")
@Tag(name = "Релизы", description = "Взаимодействие с релизами")
public class ReleaseController {

    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(ReleaseService releaseService){
        this.releaseService = releaseService;
    }

    @Operation(
            summary = "Список всех релизов",
            description = "Позволяет посмотреть список всех релизов"
    )
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Release> getReleases(){
        return releaseService.getRelease();
    }

    @Operation(
            summary = "Новый релиз",
            description = "Позволяет добавить новый релиз"
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus registerNewRelease(@RequestBody Release release){
        releaseService.addNewRelease(release);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Удаление релиза",
            description = "Позволяет удалить релиз по id"
    )
    @DeleteMapping(path = "{releaseId}")
    public HttpStatus deleteRelease(@PathVariable("releaseId") @Parameter(description = "Идентификатор") Long releaseId){
        releaseService.deleteRelease(releaseId);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Изменение релиза",
            description = "Позволяет обновить информацию о релизе"
    )
    @PutMapping(path = "{releaseId}", produces = APPLICATION_JSON_VALUE)
    public HttpStatus updateRelease(
            @PathVariable("releaseId") @Parameter(description = "Идентификатор") Long releaseId,
            @RequestParam(required = false) @Parameter(description = "Новое имя пользователя") String version,
            @RequestParam(required = false) @Parameter(description = "Новое время начала выполнения") LocalDate date_start,
            @RequestParam(required = false) @Parameter(description = "Новое время окончания выполнения") LocalDate date_finish

    ){
        releaseService.updateRelease(releaseId,version,date_start,date_finish);
        return HttpStatus.OK;
    }
}
