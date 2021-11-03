package com.example.springbootmvc.controller

import com.example.springbootmvc.DataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/app")
class Controller @Autowired constructor(val dataBase: DataBase){

    @GetMapping("/list")
    fun getList(model: Model): String {
        model.addAttribute("notes", dataBase.addressBook)
        return "app"
    }

    @PostMapping("/add")
    fun addToList(@RequestParam("name")name: String, model: Model): String {
        dataBase.addressBook[dataBase.addressBook.size] = name
        model.addAttribute("names", dataBase.addressBook)
        return "redirect:/app/list"
    }

    @GetMapping("/{id}/view")
    fun getListById(@PathVariable("id")id: Int, model: Model): String {
        model.addAttribute("names", dataBase.addressBook[id])
        return "redirect:/app/list"
    }

    @PostMapping("/delete")
    fun deleteFromList(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/delete").forward(request, response)
    }

    @PostMapping("/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int, model: Model): String {
        dataBase.addressBook.remove(id)
        model.addAttribute("notes", dataBase.addressBook)
        return "redirect:/app/list"
    }

    @PostMapping("/edit")
    fun editNote(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/edit").forward(request, response)
    }

    @PostMapping("/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestParam("name")name: String, model: Model): String {
        dataBase.addressBook[id] = name
        model.addAttribute("notes", dataBase.addressBook)
        return "redirect:/app/list"
    }
}