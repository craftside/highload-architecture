package org.highload.repository;

import lombok.RequiredArgsConstructor;
import org.highload.dto.GetUserDTO;
import org.highload.dto.NewUserDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final DataSource dataSource;

    @Override
    public boolean saveToken(String userId, String token) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_tokens (user_id, token) values (?, ?)");
            statement.setString(1, userId);
            statement.setString(2, token);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addUser( NewUserDTO newUserDTO) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (user_id, first_name, second_name, birthdate, password, biography, gender, city) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, newUserDTO.getUserId());
            statement.setString(2, newUserDTO.getFirstName());
            statement.setString(3, newUserDTO.getSecondName());
            statement.setObject(4, newUserDTO.getBirthdate());
            statement.setString(5, newUserDTO.getPassword());
            statement.setString(6, newUserDTO.getBiography());
            statement.setString(7, newUserDTO.getGender());
            statement.setString(8, newUserDTO.getCity());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GetUserDTO getUserById(String userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }

            GetUserDTO user = new GetUserDTO();
            user.setUserId(userId);
            user.setFirstName(rs.getString("first_name"));
            user.setSecondName(rs.getString("second_name"));
            user.setBirthdate(rs.getDate("birthdate").toLocalDate());
            user.setBiography(rs.getString("biography"));
            user.setGender(rs.getString("gender"));
            user.setCity(rs.getString("city"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPasswordByUserId(String userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT password from users WHERE user_id = ?");
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
