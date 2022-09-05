package com.sebqv97.myapplication.firebase_auth.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth():FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun providesGithubAuthBuilder(): OAuthProvider.Builder = OAuthProvider.newBuilder("github.com")



    @Singleton
    @Provides
    fun providesGoogleSignInOptions():GoogleSignInOptions  = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("1062178791959-8nd552eu53jq3921hgenfhumqtd24r5p.apps.googleusercontent.com")
        .requestEmail()
        .build()

    @Singleton
    @Provides
    fun providesGoogleClient(@ApplicationContext context: Context, googleSignInOptions: GoogleSignInOptions):GoogleSignInClient = GoogleSignIn.getClient(context,googleSignInOptions)



}