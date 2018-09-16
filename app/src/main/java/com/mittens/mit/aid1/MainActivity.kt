package com.mittens.mit.aid1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap


class MainActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    val mMapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fbBtn = findViewById<Button>(R.id.button2)
        fbBtn.setOnClickListener { view ->
            signIn(view, "mit.na14@gmail.com", "mitmitmit" )
        }
    }

    fun signIn(view: View,email: String, password: String){
        showMessage(view,"Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                var intent = Intent(this, LoggedInActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)

            }else{
                showMessage(view,"Erro9r: ${task.exception?.message}")
            }
        })

    }

    private fun setupMap() {
        if (mMapView != null) {
            val basemapType = Basemap.Type.STREETS_VECTOR
            val latitude = 34.05293
            val longitude = -118.24368
            val levelOfDetail = 11
            val map = ArcGISMap(basemapType, latitude, longitude, levelOfDetail)

        }
    }

    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }








//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = mAuth?.currentUser
//        updateUI(currentUser)
//    }
//
//    fun createAccount(email:String, password:String) {
//        mAuth?.createUserWithEmailAndPassword(email, password)
//                ?.addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(FragmentActivity.TAG, "createUserWithEmail:success")
//                        val user = mAuth?.currentUser
//                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(FragmentActivity.TAG, "createUserWithEmail:failure", task.exception)
//
//                        updateUI(null)
//                    }
//
//                    // ...
//                }
//    }
//
//    fun signIn(email: String, password: String){
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(FragmentActivity.TAG, "signInWithEmail:success")
//                        val user = mAuth.getCurrentUser()
//                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(FragmentActivity.TAG, "signInWithEmail:failure", task.exception)
//                        updateUI(null)
//                    }
//
//                    // ...
//                }
//    }

}
