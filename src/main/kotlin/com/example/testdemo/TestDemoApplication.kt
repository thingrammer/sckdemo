package com.example.testdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

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

