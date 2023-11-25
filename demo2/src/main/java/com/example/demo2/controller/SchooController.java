package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo2.entity.UserEntity;
import com.example.demo2.form.UserForm;
import com.example.demo2.service.UserService;

/**
 * ユーザ機能クラス.
 */
@Controller
public class SchooController {

    @Autowired
    UserService userService;

    /**
     * トップページの表示
     * 
     * @return index.htmlのパス
     */
    @GetMapping("/")
    public String index() {
        System.out.println("----- Controllerクラス index メソッド開始 -----");
        System.out.println("----- Controllerクラス index メソッド終了 -----");
        return "index";
    }

    /**
     * ユーザ情報登録処理
     * 
     * @param userForm      ユーザ用Form
     * @param bindingResult 入力チェック結果
     * @param model         次ページに渡す情報
     * @return 処理結果ページのパス
     */
    @PostMapping("user-input")
    public String regist(@Validated @ModelAttribute UserForm userForm,
            BindingResult bindingResult,
            Model model) {
        System.out.println("----- Controllerクラス regist メソッド開始 -----");
        // 今回は登録処理はなくてOKです
        // 入力値のチェックを行い結果によって次の画面を変更
        if (bindingResult.hasErrors()) {
            System.out.println("----- Controllerクラス regist メソッド終了 -----");
            return "regist-ng";
        } else {
            UserEntity userEntity = userService.createUser(userForm);
            model.addAttribute("userEntity", userEntity);
            System.out.println("----- Controllerクラス regist メソッド終了 -----");
            return "regist-ok";
        }
    }
}
