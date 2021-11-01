package com.example.spring_mvc_task.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/")
    fun redirectToLoginPage(): String {
        return "redirect:/login"
    }
}