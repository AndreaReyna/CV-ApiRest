package com.integrador.cv.services;

import com.integrador.cv.dto.HeadlineDTO;
import com.integrador.cv.entities.Headline;
import com.integrador.cv.repositories.HeadlineRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeadlineService {

    @Autowired
    private HeadlineRepository headlineRepository;

    @Transactional
    public String save(HeadlineDTO headlineDTO) throws Exception {
        try {
            validations(headlineDTO);
            ModelMapper modelMapper = new ModelMapper();
            Headline headline = modelMapper.map(headlineDTO, Headline.class);
            headline.setStatus(true);
            headlineRepository.save(headline);
            return "Titular guardado con exito: " + headline.getTitle();
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<HeadlineDTO> findAll() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<HeadlineDTO> headlinesDTO = new ArrayList();
            List<Headline> headlines = headlineRepository.findAll();
            for (Headline headline : headlines) {
                headlinesDTO.add(modelMapper.map(headline, HeadlineDTO.class));
            }
            return headlinesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    public HeadlineDTO findById(Integer id) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Headline headline = headlineRepository.findById(id).orElseThrow(() -> new Exception("No existe un titular asociado a ese id"));
            HeadlineDTO headlinesDTO = modelMapper.map(headline, HeadlineDTO.class);
            return headlinesDTO;
        } catch (Exception e) {
            throw e;
        }

    }

    @Transactional
    public void delete(Integer id) {
        try {
            headlineRepository.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }


    public void validations(HeadlineDTO headlineDTO) throws Exception {
        if (headlineDTO.getTitle() == null || headlineDTO.getTitle().isEmpty()) {
            throw new Exception("Debe tener un titular");
        }
        if (headlineDTO.getId()==null) {
         if (headlineRepository.findByHeadline(headlineDTO.getTitle()) != null) {
            throw new Exception("Ya existe un titular con ese nombre");
        }           
        }

    }
}
