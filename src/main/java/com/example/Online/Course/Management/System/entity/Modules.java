package com.example.Online.Course.Management.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moduleId;
    private String title;
    private Date createdDate;
    private Date updatedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id")
    private List<Lessons> lessonsList;
}
