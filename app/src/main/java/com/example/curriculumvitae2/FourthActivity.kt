package com.example.curriculumvitae2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        supportActionBar?.title = "Your Resume";
    }
}