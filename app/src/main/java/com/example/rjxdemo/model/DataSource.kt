package com.example.rjxdemo.model

import io.reactivex.Observable

class DataSource {
    /*fun createTaskList(): MutableList<Task> {
        val tasks = ArrayList<Task>()
        tasks.add(Task("Aakash",true,3))
        tasks.add(Task("Aman",true,3))
        tasks.add(Task("Samir",true,3))
        tasks.add(Task("Vishal",true,3))
        return tasks
    }*/

    companion object {
        fun createTaskList(): MutableList<Task> {
            val tasks = ArrayList<Task>()
            tasks.add(Task("Aakash", true, 3))
            tasks.add(Task("Aman", true, 2))
            tasks.add(Task("Samir", true, 1))
            tasks.add(Task("Vishal", true, 4))
            return tasks
        }

    }
}