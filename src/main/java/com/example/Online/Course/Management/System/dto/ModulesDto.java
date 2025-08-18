package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Lessons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModulesDto {
    private int moduleId;
    private String title;
    private Date createdDate;
    private Date updatedDate;
    private List<Lessons> lessonsList;
}
