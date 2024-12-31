package com.hermes.hermes.service;

import com.hermes.hermes.dto.User;
import com.hermes.hermes.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> getAllUsers() {
        List<User> userList = userMapper.getAllUsers();
        return userList.stream().map(user -> {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", user.getUser_id());
            userMap.put("username", user.getUser_name());
            userMap.put("userPw", user.getUser_pw());
            userMap.put("user_verification_answer",user.getUser_verification_answer());
            userMap.put("user_history",user.getUser_history());
            return userMap;
        }).collect(Collectors.toList());
    }
    @Override
    public void insertUser(User user){
        userMapper.insertUser(user);
    }

    @Override
    public User login(String user_id, String user_pw) {
        return userMapper.login(user_id,user_pw);
    }

    @Override
    public User login_search_completed(String user_name,String user_verification_answer) {
        return userMapper.login_search_completed(user_name , user_verification_answer);
    }
/*
    @Override
    public User login_search(String answer) {
        return userMapper.login_search(answer);
    }
*/

}
