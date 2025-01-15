
package com.org.application.CRUD.angular.auth;

import com.org.application.CRUD.angular.user.UserDTO;
import com.org.application.CRUD.angular.user.UserRequest;
import com.org.application.CRUD.angular.user.UserResponse;
import com.org.application.CRUD.angular.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private final AuthenticationService service;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        logger.info("[Controller] - peticion a endpoint de registro");
        AuthenticationResponse response = service.register(request);
        logger.info("[Controller] - Registro de usuario CORRECTO");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        logger.info("[Controller] - peticion a endpoint de autenticacion");
        AuthenticationResponse response = service.authenticate(request);
        logger.info("Autenticacion de usurio CORRECTA");
        return ResponseEntity.ok(response);
    }

   @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        UserDTO userDTO = userService.getUser(id);
        if(userDTO == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(userDTO);
   }

   @PutMapping("update")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest){
       UserResponse response = userService.updateUser(userRequest);
       logger.info("usuario actualizado Success - Strange3");
        return ResponseEntity.ok(response);
   }


}
