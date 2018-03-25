package com.team.mrobot.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class TModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//ID

    @NotEmpty(message = "The TModel Name Cannot Be Empty!")
    @Size(min = 1, max = 40)
    @Column(nullable = false, length = 40, unique = true)
    private String name;// TModel Name

    @NotEmpty(message = "The TModel URL Cannot Be Empty!")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String url;// TModel URL

    @NotEmpty(message = "The TModel Description Cannot Be Empty!")
    @Size(max = 128)
    @Column(nullable = false, length = 128)
    private String description;// TModel Description

    @OneToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name="id")
    private Catagory catagory;

    private String key;

    private Integer downloads;

    private Integer adoption;

    private Integer score;

    private Integer result;


    protected TModel() {
    }

    public TModel(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getAdoption() {
        return adoption;
    }

    public void setAdoption(Integer adoption) {
        this.adoption = adoption;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer isResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
