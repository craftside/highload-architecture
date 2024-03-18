package org.highload.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.highload.dto.GetUserDTO;
import org.highload.dto.NewUserDTO;
import org.highload.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public GetUserDTO getUserById(String userId) {
        log.trace("Получить пользователя по userId = {}", userId);
        return userRepository.getUserById(userId);
    }

    public String login(String userId, String password) {
        log.trace("Запрошена авторизация userId = {}", userId);
        String encodedPassword = userRepository.getPasswordByUserId(userId);
        if (encoder.matches(password, encodedPassword)) {
            String token = UUID.randomUUID().toString();
            userRepository.saveToken(userId, token);
            return token;
        }
        return null;
    }

    public String addUser(NewUserDTO newUserDTO) {
        log.trace("Добавляется новый пользователь {}", newUserDTO);

        String userId = UUID.randomUUID().toString();
        newUserDTO.setUserId(userId);

        String encodedPassword = encoder.encode(newUserDTO.getPassword());
        newUserDTO.setPassword(encodedPassword);

        userRepository.addUser(newUserDTO);
        return userId;
    }
}
