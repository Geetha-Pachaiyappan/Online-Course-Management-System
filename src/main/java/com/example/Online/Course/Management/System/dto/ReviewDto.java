package com.example.Online.Course.Management.System.dto;

import com.example.Online.Course.Management.System.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int reviewId;
    private int rating;
    private String comment;
    private Date createdDate;
    private Date updatedDate;
    private int userId;
    private int courseId;
}
