package com.example.spring_mvc_task

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import java.time.Instant
import javax.servlet.http.Cookie

@AutoConfigureMockMvc
@SpringBootTest
class SpringMvcTaskApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired
    lateinit var mock: MockMvc

    @Test
    fun addTest() {
        mock.perform(post("/app/add")
            .param("name", "Eduard")
            .param("address", "Moscow")
            .cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("redirect:/app/list")))
    }

    @Test
    fun deleteTest() {
        mock.perform(post("/app/0/delete")
            .cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("redirect:/app/list")))
    }

    @Test
    fun editTest() {
        mock.perform(post("/app/0/edit")
            .param("name", "Eduard")
            .param("address", "Moscow")
            .cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("redirect:/app/list")))
    }

    @Test
    fun listAllTest() {
        mock.perform(get("/app/list")
            .cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("app-list-page")))
    }
}


