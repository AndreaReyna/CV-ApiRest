package com.integrador.cv.services;

import com.integrador.cv.dto.UserDTO;
import com.integrador.cv.entities.User;
import com.integrador.cv.enums.Role;
import com.integrador.cv.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public String save(UserDTO userdto) throws Exception {
        validations(userdto);
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(encoder.encode(userdto.getPassword()));
        if (userRepository.findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        userRepository.save(user);

        return "Registro exitoso";
    }


    public void validations(UserDTO userdto) throws Exception {
        if (userRepository.findByUsername(userdto.getUsername()) != null) {
            throw new Exception("Ese nombre de usuario ya se encuentra registrado");
        }
        if (userdto.getPassword() == null) {
            throw new Exception("Debe ingresar una contraseña");
        }
        if (userdto.getPassword() .length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
    }
}
