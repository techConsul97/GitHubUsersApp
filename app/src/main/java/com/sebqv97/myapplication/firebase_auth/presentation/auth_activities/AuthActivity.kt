package com.sebqv97.myapplication.firebase_auth.presentation.auth_activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AuthActivity ():ComponentActivity() {
    @Inject lateinit var  firebaseAuth:FirebaseAuth
    @Inject lateinit var provider: OAuthProvider.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult
        if(pendingResultTask != null)
        {
            pendingResultTask
                .addOnSuccessListener(
                    OnSuccessListener<AuthResult?> {
                        // User is signed in.
                        // IdP data available in
                        // authResult.getAdditionalUserInfo().getProfile().
                        // The OAuth access token can also be retrieved:
                        // authResult.getCredential().getAccessToken().
                    })
                .addOnFailureListener(
                    OnFailureListener {
                        // Handle failure.
                    })
        }
        else{
            firebaseAuth
                .startActivityForSignInWithProvider( /* activity= */this, provider.build())
                .addOnSuccessListener {
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // authResult.getCredential().getAccessToken().
                }
                .addOnFailureListener {
                    // Handle failure.
                }
        }

    }
}