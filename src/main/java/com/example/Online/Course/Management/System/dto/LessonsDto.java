package com.example.Online.Course.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonsDto {

    private int lessonId;
    private String title;
    private String videoUrl;
    private int durationInMinutes;

    private Date createdDate;
    private Date updatedDate;
}
