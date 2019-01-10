package com.example.testdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@ComponentScan(
        "com.demo.jwt"
)
@SpringBootApplication
class TestDemoApplication

fun main(args: Array<String>) {
    runApplication<TestDemoApplication>(*args)
}

@RestController
class MainController {
    @GetMapping("/")
    fun index(): Any {

//        var h : UriHandler
        return ""

    }

}

