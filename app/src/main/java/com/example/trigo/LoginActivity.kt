package com.example.trigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class Login : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        textView_signup.setOnClickListener {
            intent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }
        button_login.setOnClickListener{
          performLogin()
        }
    }

    private fun performLogin() {
        val email=email_login.text.toString()
        val password=password_login.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                val intent = Intent(this,HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnCompleteListener {
                if (it.isSuccessful) return@addOnCompleteListener
            }
            .addOnFailureListener {
                Toast.makeText(this,"Check Email and password", Toast.LENGTH_SHORT).show()
            }
    }

}
