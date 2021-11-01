package com.example.spring_mvc_task

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class SpringMvcTaskApplication

fun main(args: Array<String>) {
    runApplication<SpringMvcTaskApplication>(*args)
}
