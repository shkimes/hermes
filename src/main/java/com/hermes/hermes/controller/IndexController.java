package com.hermes.hermes.controller;


import com.hermes.hermes.dto.User;
import com.hermes.hermes.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexController {
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
        System.out.println(user);
        if(user!=null){
            session.setAttribute("loggedInUser", user);
            return "redirect:/";
        }else {
            model.addAttribute("fail","유효하지 않은 아이디 또는 비밀번호 입니다");
            return "login";
        }
    }

    @GetMapping("/login_search")
    public String loginBySearch() {
        return "login_search";
    }



    @PostMapping("/login_search")
    public String login_search(@RequestParam String question, Model model, HttpSession session) {
        User user = userService.login_search(question);
        if(user!=null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/login_search_completed";
        }else{
            model.addAttribute("fail","올바르지 못한 답변입니다");
            return "login_search";
        }

    }
    @GetMapping("/login_search_completed")
    public String login_search_completed(){
        return "login_search_completed";
    }
    @PostMapping("/login_search_completed")
    public String login_search_completed(@RequestParam String user_id,
                                         @RequestParam String user_pw,
                                         Model model,
                                         HttpSession session) {
        User user = userService.login_search_completed(user_id, user_pw);
        session.setAttribute("user", user);

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