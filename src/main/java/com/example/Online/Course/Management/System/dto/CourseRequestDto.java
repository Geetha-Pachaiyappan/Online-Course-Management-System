package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Category;
import com.example.Online.Course.Management.System.entity.Modules;
import com.example.Online.Course.Management.System.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {
    private String title;
    private String description;
    private double price;
    private Integer user;
    private List<Integer> categoryIdsList;
    private List<Modules> modules;
}
