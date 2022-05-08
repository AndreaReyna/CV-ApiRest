package com.integrador.cv.controllers;

import com.integrador.cv.dto.HeaderDTO;
import com.integrador.cv.services.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headers")
public class HeaderController {
    
    @Autowired
    HeaderService headerService;

    @PostMapping()
    public void save(@RequestBody HeaderDTO dto) throws Exception {
        headerService.save(dto);
    }

    @GetMapping("/{id}")
    public HeaderDTO findById(@PathVariable("id") Integer id) throws Exception {
        return this.headerService.findById(id);
    }

}
