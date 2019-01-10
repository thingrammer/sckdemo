package com.example.testdemo

import com.demo.jwt.service.impl.UserDetailsServiceAdapter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@SpringBootApplication
(
        scanBasePackages = [
            "com.demo.jwt"
            , "com.example"
        ]
)
class TestDemoApplication

fun main(args: Array<String>) {
    runApplication<TestDemoApplication>(*args)
}

@Service
@Primary
class UserDetailsServiceImpl : UserDetailsServiceAdapter() {
    companion object {
        val userData = mutableMapOf(
                "fat" to BCryptPasswordEncoder().encode("pss")
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val password = userData[username]
                ?: throw UsernameNotFoundException(username)
        return User(
                username,
                password,
                emptyList()
        )
    }

    override fun saveUser(username: String, password: String) {

    }
}

