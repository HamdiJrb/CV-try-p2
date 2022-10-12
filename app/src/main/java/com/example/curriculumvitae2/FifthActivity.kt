package com.example.curriculumvitae2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.curriculumvitae2.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {
    private var uploaded: ImageView? = null  //image Gallery uploaded

    lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(LayoutInflater)
        setContentView(binding.root)
        uploaded=findViewById(R.id.ImgGallery)

        binding.btnSkills.setOnClickListener {
            replaceFragment(fragmentSkills())
        }
        binding.btnHobbies.setOnClickListener {
            replaceFragment(fragmentHobbies())
        }
        binding.btnLanguages.setOnClickListener {
            replaceFragment(fragmentLanguages())
        }

    }
    // fct qui remplace le fragmentContainer par le fragment donné en parametre
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentContainer , fragment)
        fragmentTransaction.commit()
    }
}