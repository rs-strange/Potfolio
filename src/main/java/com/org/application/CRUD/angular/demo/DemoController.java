package com.org.application.CRUD.angular.demo;

import com.org.application.CRUD.angular.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DemoController {

    @GetMapping("")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Saludo desde endpoint not secured");
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstname("kania");
        userDTO.setUsername("uchiha");
        userDTO.setEmail("kania@domain.com");
        return ResponseEntity.ok(userDTO);
    }


}
