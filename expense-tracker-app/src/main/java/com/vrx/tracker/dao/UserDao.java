package com.vrx.tracker.dao;

import com.vrx.tracker.dto.UserDto;
import com.vrx.tracker.entity.User;

public interface UserDao {
    public UserDto saveUser(UserDto userDto);
    public User getUserByUserId(String userId);
}
