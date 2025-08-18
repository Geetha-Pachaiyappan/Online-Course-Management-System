package com.example.Online.Course.Management.System.service;

import com.example.Online.Course.Management.System.dto.ModulesDto;
import com.example.Online.Course.Management.System.entity.Modules;
import com.example.Online.Course.Management.System.repository.CourseModuleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModulesService {
    @Autowired
    private CourseModuleRepository moduleRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<ModulesDto> saveAllModules(List<ModulesDto> modulesDtos){
        List<Modules> modules  = modulesDtos.stream()
                .map(modulesDto ->modelMapper.map(modulesDto, Modules.class)).toList();

        List<Modules> savedModules = moduleRepo.saveAll(modules);

        return savedModules.stream()
                .map(module -> modelMapper.map(module, ModulesDto.class))
                .toList();
    }

    public List<ModulesDto> getAllModules(){
        List<Modules> modulesList = moduleRepo.findAll();
        return modulesList.stream()
                .map(module -> modelMapper.map(module, ModulesDto.class))
                .toList();
    }
}
