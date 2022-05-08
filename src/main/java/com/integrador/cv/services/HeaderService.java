package com.integrador.cv.services;

import com.integrador.cv.dto.HeaderDTO;
import com.integrador.cv.entities.Header;
import com.integrador.cv.repositories.HeaderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeaderService {
    
    @Autowired
    HeaderRepository headerRepository;

    public HeaderDTO save(HeaderDTO headerDTO) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        Header header = modelMapper.map(headerDTO, Header.class);

        headerRepository.save(header);
        headerDTO.setId(header.getId());
        return headerDTO;
    }

    public HeaderDTO findById(Integer id) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            Header header = headerRepository.findById(id).orElseThrow(() -> new Exception("No existe un encabezado asociado a ese id"));
            if (header == null) {
                throw new Exception();
            }
            HeaderDTO headerDTO = mp.map(header, HeaderDTO.class);
            return headerDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}

