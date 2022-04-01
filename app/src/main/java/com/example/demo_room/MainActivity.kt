package com.example.demo_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.demo_room.room.MyDataBase
import com.example.demo_room.room.User
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val show = findViewById<TextView>(R.id.show)
        val addButton = findViewById<Button>(R.id.add)
        val queryButton = findViewById<Button>(R.id.query)

        addButton.setOnClickListener {
            addUser()
        }

        queryButton.setOnClickListener {
            val showText = queryUser()
            show.text = showText
        }
    }

    fun addUser() {
        // 启动一个子线程
        thread {
            // 从数据库类中获取Dao接口，再用Dao访问数据库
            val dao = MyDataBase.getDataBase(this).userDao()
            val user = User(name = "小明", age = 18)
            dao.insertUser(user)
        }
    }

    fun queryUser(): String {
        var showText = ""
        // 启动一个子线程
        thread {
            // 从数据库类中获取Dao接口，再用Dao访问数据库
            val dao = MyDataBase.getDataBase(this).userDao()
            val list = dao.getAllUser()
            for (user in list) {
                showText += "名字：${user.name}，年龄：${user.age}，id：${user.id}\n\n"
            }
        }.join()  // 为了获取子线程中查询到的结果，此处简单的使用join等待子线程完成，再结束函数
        return showText
    }
}