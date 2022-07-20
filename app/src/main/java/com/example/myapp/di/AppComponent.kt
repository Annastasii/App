package com.example.myapp.di

import com.example.myapp.MainActivity
import dagger.Component

@Component(modules = [RemoteModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}