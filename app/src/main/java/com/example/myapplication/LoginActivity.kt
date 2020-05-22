package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_sign.setOnClickListener {
            val email = et_email_login.text.toString()
            val password = et_password_login.text.toString()


            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(this, "please enter your email/password", Toast.LENGTH_SHORT).show()
            return@setOnClickListener

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Login","Sign in with uid: ${it.result?.user?.uid}")

                    val intent = Intent(this,ProfileActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Log.d("Main","Faild to sign in: ${it.message}")
                    Toast.makeText(this, "Faild to sign in: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
        tv_signUp.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}