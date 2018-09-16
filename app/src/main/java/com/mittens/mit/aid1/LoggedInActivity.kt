package com.mittens.mit.aid1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class LoggedInActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        var btnLogOut = findViewById<Button>(R.id.btnLogout)

        btnLogOut.setOnClickListener { view ->
            showMessage(view, "Logging out...")
            signOut()
        }

        fbAuth.addAuthStateListener {
            if(fbAuth.currentUser== null){
                this.finish()
            }
        }
    }

    fun signOut(){
        fbAuth.signOut()
    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}
