package com.integrador.cv.services;

import com.integrador.cv.dto.ContentDTO;
import com.integrador.cv.entities.Content;
import com.integrador.cv.repositories.ContentRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Transactional
    public String save(ContentDTO contentDTO) throws Exception {
        try {
            validations(contentDTO);
            ModelMapper modelMapper = new ModelMapper();
            Content content = modelMapper.map(contentDTO, Content.class);
            content.setStatus(true);
            contentRepository.save(content);
            return "Contenido guardado con exito: " + content.getTitle();
        } catch (Exception e) {
             throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ContentDTO> findAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<ContentDTO> contentsDTO = new ArrayList();
            List<Content> contents = contentRepository.findAll();
            for (Content c : contents) {
                contentsDTO.add(modelMapper.map(c, ContentDTO.class));
            }
            return contentsDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ContentDTO> contentsByHeadline(String headline) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            List<ContentDTO> contentsDTO = new ArrayList();
            List<Content> contents = contentRepository.findByHeadline(headline);
            if (contents.isEmpty()) {
                throw new Exception("No existen contenidos asociados a ese ID");
            }
            for (Content c : contents) {
                contentsDTO.add(mp.map(c, ContentDTO.class));
            }
            return contentsDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    public ContentDTO findById(Integer id) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Content content = contentRepository.findById(id).orElseThrow(() -> new Exception("No existe un contenido asociado a ese id"));
            ContentDTO contentDTO = modelMapper.map(content, ContentDTO.class);
            return contentDTO;
        } catch (Exception e) {
            throw e;
        }

    }

    @Transactional
    public void delete(Integer id) {
        try {
            contentRepository.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void validations(ContentDTO dto) throws Exception {
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new Exception("Debe tener un titulo");
        }
        if (dto.getHeadline().getId() == null) {
            throw new Exception("Debe asociarse a un titular");
        }
        if (dto.getStartDate()!=null) {
            if (dto.getEndDate()!=null) {
                if (dto.getStartDate().isAfter(dto.getEndDate())) {
                    throw new Exception("La fecha de inicio no puede ser posterior a la de fin");
                }
            }
        }
    }
}
