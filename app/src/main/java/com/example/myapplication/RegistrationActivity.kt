package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btn_registration.setOnClickListener {
            val email = et_email_registration.text.toString()
            val password = et_password_registration.text.toString()
            val userName = et_userName.text.toString()

            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(this, "please enter your email or password", Toast.LENGTH_SHORT).show()
            return@setOnClickListener

            Log.d("Main Activity", "Email is" + email)
            Log.d("Main Activity ", "Password: $password")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener

                        Log.d("Main","Successfully created user and his uid: ${it.result?.user?.uid}")
                        Toast.makeText(this, "Successfully creating account", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,ProfileActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Log.d("Main","Faild to create user: ${it.message}")
                        Toast.makeText(this, "Faild to create account", Toast.LENGTH_SHORT).show()
                    }

        }

        tv_already_have_account.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}