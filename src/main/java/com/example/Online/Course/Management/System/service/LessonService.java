package com.example.Online.Course.Management.System.service;

import com.example.Online.Course.Management.System.dto.LessonsDto;
import com.example.Online.Course.Management.System.entity.Lessons;
import com.example.Online.Course.Management.System.repository.LessonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepo;
    @Autowired
    private ModelMapper modelMapper;


    public List<LessonsDto> saveAllLessons(List<LessonsDto> lessonsDtos){
        List<Lessons> lessonsList  = lessonsDtos.stream()
                .map(lessonDto ->modelMapper.map(lessonDto, Lessons.class)).toList();

        List<Lessons> savedLessons = lessonRepo.saveAll(lessonsList);

        return savedLessons.stream()
                .map(lessons -> modelMapper.map(lessons, LessonsDto.class))
                .toList();
    }

    public List<LessonsDto> getAllLessons(){
        List<Lessons> lessonsList = lessonRepo.findAll();
        return lessonsList.stream()
                .map(lessons -> modelMapper.map(lessons, LessonsDto.class))
                .toList();
    }
}
