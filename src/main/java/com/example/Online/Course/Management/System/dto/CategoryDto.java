package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private List<Course> courseList;
}
