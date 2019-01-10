package com.example.testdemo

import com.demo.jwt.config.security.JwtAuth
import com.demo.jwt.config.security.JwtProperties
import com.demo.jwt.config.security.UriHandler
import com.demo.jwt.service.impl.UserDetailsServiceAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @JwtAuth
    @GetMapping("/")
    fun some(): Any {
        return JwtProperties
    }

    @GetMapping("/else")
    fun index(): Any {
//        var h : UriHandler
        return UriHandler.uris

    }

    @Autowired
    lateinit var userDetailsService: UserDetailsServiceAdapter

    @PostMapping("/sign/up")
    fun signUp(@RequestBody user: Map<String, String>): Any {
        userDetailsService.saveUser(user["username"]!!, user["password"]!!)
        return 0
    }
}
