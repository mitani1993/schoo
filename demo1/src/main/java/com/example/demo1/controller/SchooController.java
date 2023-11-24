package com.example.demo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo1.entity.UserList;
import com.example.demo1.service.UserListService;

/**
 * Springで作成する簡単なDB出力のサンプルクラスです.
 */
@Controller
public class SchooController {

    @Autowired
    UserListService userListService;

    /**
     * トップページへのリクエスト
     *
     * @return output.htmlのパス
     */
    @GetMapping("/")
    public String index(Model model) {
        List<UserList> users = userListService.findAll();
        List<UserList> usersDesc = userListService.findAllDesc();
        UserList userOne = userListService.findByUserOne();
        // 検索結果を表示
        model.addAttribute("users", users);
        model.addAttribute("usersDesc", usersDesc);
        model.addAttribute("userOne", userOne);
        return "output";
    }
}
