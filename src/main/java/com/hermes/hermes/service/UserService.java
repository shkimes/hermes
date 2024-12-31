package com.hermes.hermes.service;

import com.hermes.hermes.dto.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> getAllUsers();

    // 유저 저장하기 기능
    void insertUser(User user);

    // 유저 로그인 기능
    User login(String user_id, String user_pw);

    // 질문으로 유저 찾기 기능
   // User login_search(String answer);

    // 결과 페이지 보여주기 기능
    User login_search_completed(String user_pw,String user_verification_answer);
}
