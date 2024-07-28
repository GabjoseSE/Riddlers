package com.example.riddlers

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.riddlers.databinding.ActivityRegistrationUiBinding
import com.google.firebase.auth.FirebaseAuth

class registrationUI : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrationUiBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationUiBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvSignIn.setOnClickListener{
            val intent = Intent (this, loginUI::class.java)
            startActivity(intent)
        }


        binding.btnRegisterButton.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val re_type_password = binding.etRetypePassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && re_type_password.isNotEmpty()){
                if (password == re_type_password){
                    firebaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent (this, registrationUI::class.java)
                            startActivity(intent)
                            
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password is not match", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please make a Password", Toast.LENGTH_SHORT).show()
            }
        }








        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}