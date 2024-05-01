package com.vrx.tracker.service.impl;

import com.vrx.tracker.dao.UserDao;
import com.vrx.tracker.dto.UserDto;
import com.vrx.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto saveUser(UserDto userDto) {
        return userDao.saveUser(userDto);
    }
}
