package com.romanyuta.todoproject.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Schema(description = "Сущность проекта")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Заказчик")
    private String customer;

    @Schema(description = "Название проекта")
    private String title;

    @Schema(description = "Дата заключения контракта")
    private LocalDate contract_date;

    @ManyToOne
    @JoinColumn(name = "id_release")
    @Schema(description = "Текущий релиз")
    private Release release;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Project() {
    }

    public Project(String customer, String title, LocalDate contract_date, Release release) {
        this.customer = customer;
        this.title = title;
        this.contract_date = contract_date;
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getContract_date() {
        return contract_date;
    }

    public void setContract_date(LocalDate contract_date) {
        this.contract_date = contract_date;
    }

}
