package org.highload.repository;

import org.highload.dto.GetUserDTO;
import org.highload.dto.NewUserDTO;

public interface UserRepository {

    boolean saveToken(String userId, String token);

    boolean addUser(NewUserDTO newUserDTO);

    GetUserDTO getUserById(String userId);

    String getPasswordByUserId(String userId);

}
