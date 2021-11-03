package com.example.springbootmvc

import org.springframework.stereotype.Component
import java.time.Instant
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@WebServlet("/login")
class LoginServlet : HttpServlet() {
    private val login = "l"
    private val password = "p"

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.getRequestDispatcher("/auth.html").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
//        val login = req.getParameter("login")
//        val password = req.getParameter("password")
        if (login == req.getParameter("login") && password == req.getParameter("password")) {
            val cookie = Cookie("auth", Instant.now().toEpochMilli().toString())
            cookie.maxAge = 60
            resp.addCookie(cookie)
            resp.sendRedirect("/app/list")
        } else {
            resp.sendRedirect("/login")
        }
    }
}