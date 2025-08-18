package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.LessonsDto;
import com.example.Online.Course.Management.System.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonsController {
    @Autowired
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<List<LessonsDto>> saveAllLessons(@RequestBody List<LessonsDto> lessonsList){
        return new ResponseEntity<>(lessonService.saveAllLessons(lessonsList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LessonsDto>> getAllLessons(){
        return new ResponseEntity<>(lessonService.getAllLessons(), HttpStatus.OK);
    }
}
