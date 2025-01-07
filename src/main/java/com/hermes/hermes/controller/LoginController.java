package com.hermes.hermes.controller;


import com.hermes.hermes.dto.User;
import com.hermes.hermes.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate(); // invalidate 로그인된 정보를 초기화하면서 없애기
        return "redirect:/";    // 로그아웃 된 정보를 재설정하면서 index.html 돌아가기
    }

    @ModelAttribute("loggedInUser")
    public Object addLoggedInUserToModel(HttpSession session) {
        return session.getAttribute("loggedInUser");
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("user_id") String user_id,
                        @RequestParam("user_pw") String user_pw,
                        Model model,
                        HttpSession session) {
        User user = userService.login(user_id, user_pw);

        if(user!=null){
            session.setAttribute("loggedInUser", user);
            System.out.println(session.getAttribute("loggedInUser"));
            return "redirect:/";
        }else {
            model.addAttribute("error","유효하지 않은 아이디 또는 비밀번호 입니다");
            return "login";
        }
    }

    @GetMapping("/login_search")
    public String loginBySearch() {
        return "login_search";
    }



/*
    @PostMapping("/login_search")
    public String login_search(@RequestParam String answer, Model model, HttpSession session) {
        User user = userService.login_search(answer);
        if(user!=null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/login_search_completed";
        }else{
            model.addAttribute("fail","올바르지 못한 답변입니다");
            return "login_search";
        }

    }
*/

    @GetMapping("/login_search_completed")
    public String login_search_completed(@RequestParam("user_name")String user_name,
                                         @RequestParam("user_verification_answer")String user_verification_answer,
                                         Model model) {
        User user = userService.login_search_completed(user_name, user_verification_answer);
        System.out.println("user : " + user);
        model.addAttribute("user", user);
        return "login_search_completed";
    }


    @PostMapping("/signup-success")
    public String signup(@ModelAttribute User user) {
        if (user.getUser_id() == null || user.getUser_id().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }

        userService.insertUser(user);
        return "signup-Success";
    }



}