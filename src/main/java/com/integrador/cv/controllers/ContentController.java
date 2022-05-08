package com.integrador.cv.controllers;

import com.integrador.cv.dto.ContentDTO;
import com.integrador.cv.services.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping()
    public List<ContentDTO> findAll() {
        return contentService.findAll();
    }

    @GetMapping("/search/{headline}")
    public List<ContentDTO> contentsByHeadline(@PathVariable("headline") String headline) throws Exception {
        return contentService.contentsByHeadline(headline);
    }

    @PostMapping()
    public void save(@RequestBody ContentDTO dto) throws Exception {
         contentService.save(dto);
    }

    @GetMapping("/{id}")
    public ContentDTO findById(@PathVariable("id") Integer id) throws Exception {
        return this.contentService.findById(id);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        contentService.delete(id);
    }
}
