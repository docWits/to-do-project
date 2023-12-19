package com.romanyuta.todoproject.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Schema(description = "Сущность задачи")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Название задачи")
    private String title;

    @Schema(description = "Статус")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_project")
    @Schema(description = "Проект")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id_release")
    @Schema(description = "Текущий релиз")
    private Release release;

    @ManyToOne
    @JoinColumn(name = "id_author")
    @Schema(description = "Автор задачи")
    private Users author;

    @ManyToOne
    @JoinColumn(name = "id_worker")
    @Schema(description = "Исполнитель задачи")
    private Users worker;


    public Task() {
    }

    public Task(String title, String status, Project project, Release release, Users author, Users worker) {
        this.title = title;
        this.status = status;
        this.project = project;
        this.release = release;
        this.author = author;
        this.worker = worker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Users getWorker() {
        return worker;
    }

    public void setWorker(Users worker) {
        this.worker = worker;
    }
}
