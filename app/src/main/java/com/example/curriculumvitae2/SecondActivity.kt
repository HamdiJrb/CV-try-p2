package com.example.curriculumvitae2

import android.accessibilityservice.GestureDescription
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


const val NAME="NAME"
const val EMAIL="NAME"
const val AGE="NAME"
const val GENDER="NAME"
const val SKILLSA="NAME"
const val SKILLSI="NAME"
const val SKILLSF="NAME"
const val LANG="NAME"
const val HOBB="NAME"

class SecondActivity : AppCompatActivity() {
    private var fullName="NONE"
    private var errorName: TextInputLayout?=null
    private var email="NONE"
    private var errorEmail: TextInputLayout?=null
    private var age="NONE"
    private var errorAge: TextInputLayout?=null

    private var male: RadioButton? = null
    private var female: RadioButton? = null

    private var next: Button? = null
    private var upload: ImageView? = null  //image Gallery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Create your Resume"

        fullName=intent.getStringExtra(NAME).toString()
        errorName=findViewById(R.id.FullName)
        email=intent.getStringExtra(EMAIL).toString()
        errorEmail=findViewById(R.id.Email)
        age=intent.getStringExtra(AGE).toString()
        errorAge=findViewById(R.id.Age)

        male=findViewById(R.id.Male)
        female=findViewById(R.id.Female)
        
        next=findViewById(R.id.Next)
        upload=findViewById(R.id.UserPic)   //gallery
        
        next!!.setOnClickListener { 
            clickNext()
        }

        upload!!.setOnClickListener {
            UploadImage()
        }

    }
////////////////////// fct UploadImage ///////////////////////////////

    private fun UploadImage(context: Context) {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder: AlertDialog.Builder = GestureDescription.Builder(context)
        builder.setTitle("Choose your profile picture")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, 0)
            } else if (options[item] == "Choose from Gallery") {
                val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, 1)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!["data"] as Bitmap?
                    ImageView.setImageBitmap(selectedImage)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? = contentResolver.query(
                            selectedImage,
                            filePathColumn, null, null, null
                        )
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            ImageView.SetImageBitmap(BitmapFactory.decodeFile(picturePath))
                            cursor.close()
                        }
                    }
                }
            }
        }
    }
    /////////////////////// fct clickNext////////////////////////////////

    private fun clickNext() {
        if(validate()){
        val intent = Intent(this,ThirdScreen::class.java).apply {
            putExtra(NAME,fullName)
            putExtra(EMAIL,email)
            putExtra(AGE,age)
        }
        startActivity(intent)
        }

    }

    private fun validate():Boolean {
        /*var name:Boolean=true
        var mail:Boolean=true
        var Age:Boolean=true*/
        /*if(fullName?.text!!.isEmpty()){
            errorName?.error="Must not be empty !"
            name=false
        }
        if(email?.text!!.isEmpty()){
            errorEmail?.error="Must not be empty !"
            mail=false
        }
        if(age?.text!!.isEmpty()){
            errorAge?.error="Must not be empty !"
            Age=false
        }

       *//* if(!male?.isChecked!! && !female?.isChecked!! ){
            Toast.makeText(this, "Choose your gender !", Toast.LENGTH_SHORT).show()
            return false
        }*//*
        if(EMAIL_ADDRESS.matcher(email?.text!!).matches()){
            errorEmail?.error="Check you email !"
            mail= false
        }

        if (name===false || mail===false||Age===false ){
          return false
        }
        return true

    }*/
        return true
    }
}