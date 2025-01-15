package com.org.application.CRUD.angular.user;

import com.org.application.CRUD.angular.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {


    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UsuarioRepository userRepository;

    @Transactional
    public UserResponse updateUser(UserRequest userRequest){
        logger.info("Actualizando usuario");
        Usuario user = Usuario.builder()
                .id(userRequest.getId())
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .build();

        userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
        return new UserResponse("El usuario se registro satisfactoriamente");
    }

    public UserDTO getUser(Long id){
        logger.info("Obteniendo usuario");
        Usuario user = userRepository.findById(id).orElse(null);

        if(user != null){
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .build();

            return userDTO;
        }

        return null;
    }
}
