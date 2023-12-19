package com.romanyuta.todoproject.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;

@Entity
@Schema(description = "Сущность пользователя")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Имя пользователя")
    private String name;

    @Schema(description = "Роль пользователя")
    private String role;

    @OneToMany(mappedBy = "author")
    private List<Task> tasksAuthors;

    @OneToMany(mappedBy = "worker")
    private List<Task> tasksWorkers;

    public Users() {
    }

    public Users(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
