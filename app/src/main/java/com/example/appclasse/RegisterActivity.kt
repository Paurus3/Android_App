package com.example.appclasse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AbsSeekBar
import android.widget.EditText
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtName=findViewById(R.id.txtName)
        txtLastName=findViewById(R.id.txtLastName)
        txtPassword=findViewById(R.id.txtPassword)
        txtEmail=findViewById(R.id.txtEmail)

        progressBar= findViewById(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")

    }


    fun register(view:View)
    {


    }

    private fun createAccount()
    {
        val name:String = txtName.text.toString()
        val lastName:String = txtLastName.text.toString()
        val email:String = txtEmail.text.toString()
        val password:String = txtPassword.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            progressBar.visibility = View.VISIBLE

            auth.c

        }
    }
}