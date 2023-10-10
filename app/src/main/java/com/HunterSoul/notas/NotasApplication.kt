package com.HunterSoul.notas

import android.app.Application
import com.HunterSoul.notas.data.DefaultAppContainer

class NotasApplication : Application() {
    lateinit var container: DefaultAppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}