package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Course;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Category Should not be null")
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private List<Course> courseList;
}
