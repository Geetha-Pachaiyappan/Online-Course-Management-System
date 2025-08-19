package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Category;
import com.example.Online.Course.Management.System.entity.Modules;
import com.example.Online.Course.Management.System.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {
    private int courseId;
    private String title;
    private String description;
    private double price;
    private int availableSeats;
    private Date createdDate;
    private Date updatedDate;
//    private List<CategoryDto> categoryList;
    private UserResponseDto user;
    private List<Modules> modules;
}
