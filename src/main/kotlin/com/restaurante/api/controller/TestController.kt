package com.restaurante.api.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class TestController {

    @GetMapping("/test")
    fun test(): String{
        return "API Funcionando"
    }
}