package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.Category;
import com.example.Online.Course.Management.System.entity.Modules;
import com.example.Online.Course.Management.System.entity.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {
    @NotNull(message = "Title should not be null")
    @Size(min = 6, message = "Title should be least 6 characters")
    private String title;

    @NotNull(message = "Description should not be null")
    @Size(min = 15, message = "Description should be least 15 characters")
    private String description;
    //@Positive
    @NotNull
    @PositiveOrZero
    private double price;
    private Integer user;
    private int availableSeats;
    private List<Integer> categoryIdsList;
    private List<Modules> modules;
}
