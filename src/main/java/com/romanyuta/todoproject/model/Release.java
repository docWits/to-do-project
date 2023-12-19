package com.romanyuta.todoproject.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Schema(description = "Сущность релиза")
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Время начала выполнения работ")
    private LocalDate date_start;

    @Schema(description = "Время окончания выполнения работ")
    private LocalDate date_finish;

    @OneToMany(mappedBy = "release")
    private List<Task> tasks;

    @OneToMany(mappedBy = "release")
    private List<Project> projects;


    public Release() {
    }

    public Release(String version, LocalDate date_start, LocalDate date_finish) {
        this.version = version;
        this.date_start = date_start;
        this.date_finish = date_finish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(LocalDate date_finish) {
        this.date_finish = date_finish;
    }
}
