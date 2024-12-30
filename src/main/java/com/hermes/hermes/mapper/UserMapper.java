package com.hermes.hermes.mapper;

import com.hermes.hermes.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    // 유저 저장하기
    void insertUser(User user);
    // userMapper.xml 에 작성한 SQL 문 연결하는 메서드 명칭 작성
    User login(String user_id,String user_pw);
    // 문답으로 유저 찾기
    User login_search(String question);

    // 결과 페이지에서 아이디 비밀번호 보여주기
    User login_search_completed(String user_id, String user_pw);
}
