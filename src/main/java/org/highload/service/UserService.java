package org.highload.service;

import org.highload.dto.GetUserDTO;
import org.highload.dto.NewUserDTO;

public interface UserService {
    GetUserDTO getUserById(String userId);

    String login(String id, String password);

    String addUser(NewUserDTO newUserDTO);
}
