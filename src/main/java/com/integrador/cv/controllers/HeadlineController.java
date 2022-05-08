package com.integrador.cv.controllers;

import com.integrador.cv.dto.HeadlineDTO;
import com.integrador.cv.services.HeadlineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headlines")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @GetMapping()
    public List<HeadlineDTO> findAll() {
        return headlineService.findAll();
    }

    @PostMapping()
    public void save(@RequestBody HeadlineDTO dto) throws Exception {
         headlineService.save(dto);
    }

    @GetMapping("/search/{id}")
    public HeadlineDTO findById(@PathVariable("id") Integer id) throws Exception {
        return this.headlineService.findById(id);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        headlineService.delete(id);
    }
}
