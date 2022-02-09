package com.example.appclasse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        txtEmail=findViewById(R.id.username2)
        auth= FirebaseAuth.getInstance()

    }

    fun resetPassword(view: View)
    {
        val email:String=txtEmail.text.toString()
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful)
            {
                Toast.makeText(this, "Mail send successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }else
            {
                Toast.makeText(this, "This email don't have an account", Toast.LENGTH_LONG).show()
            }
        }
    }

}