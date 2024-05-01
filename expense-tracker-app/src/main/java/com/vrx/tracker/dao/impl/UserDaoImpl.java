package com.vrx.tracker.dao.impl;

import com.vrx.tracker.dao.UserDao;
import com.vrx.tracker.dto.UserDto;
import com.vrx.tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;


    public UserDto saveUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        String sql = "INSERT INTO users(user_id, username) VALUES(?,?)";
        int rows = template.update(sql, userDto.getUserId(), userDto.getUsername());
        if (rows > 0) {
            return userDto;
        }
        return null;
    }

    public User getUserByUserId(String userId) {
        String sql = "SELECT * from users WHERE user_id=?";
        User userResponse = template.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setUsername(rs.getString("username"));
            return user;
        });
        return userResponse;
    }
}
