package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.ModulesDto;
import com.example.Online.Course.Management.System.service.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModulesController {
    @Autowired
    private ModulesService modulesService;

    @PostMapping
    public ResponseEntity<List<ModulesDto>> saveAllModules(@RequestBody List<ModulesDto> mosuleList){
        return new ResponseEntity<>(modulesService.saveAllModules(mosuleList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ModulesDto>> getAllModules(){
        return new ResponseEntity<>(modulesService.getAllModules(), HttpStatus.OK);
    }
}
