package com.example.spring_mvc_task.controllers

import com.example.spring_mvc_task.models.AddressBook
import com.example.spring_mvc_task.models.Note
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class RequestController @Autowired constructor(val addressBook: AddressBook) {

    @GetMapping("/list")
    fun getList() = addressBook.list

    @PostMapping("/add")
    fun addToList(@RequestBody note: Note) {
        addressBook.list[addressBook.list.size] = note
    }

    @GetMapping("/{id}/view")
    fun getListById(@PathVariable("id")id: Int) = addressBook.list[id]

    @PostMapping("/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int) {
        addressBook.list.remove(id)
    }

    @PostMapping("/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestBody note: Note) {
        addressBook.list[id] = note
    }
}