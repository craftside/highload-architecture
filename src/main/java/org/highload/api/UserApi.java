package org.highload.api;

import lombok.RequiredArgsConstructor;
import org.highload.dto.GetUserDTO;
import org.highload.dto.NewUserDTO;
import org.highload.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping(value = "/user/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserDTO> getUserById(@PathVariable("id") String id) {
        try {
            GetUserDTO user = userService.getUserById(id);
            return user != null
                    ? ResponseEntity.ok().body(user)
                    : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/user/register")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody NewUserDTO newUserDTO) {
        try {
            return ResponseEntity.ok().body(Map.of("user_id",  userService.addUser(newUserDTO)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@PathParam("id") String id,
                                          @PathParam("password") String password) {
        try {
            String token = userService.login(id, password);
            return token != null
                    ? ResponseEntity.ok().body(Map.of("token", token))
                    : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}