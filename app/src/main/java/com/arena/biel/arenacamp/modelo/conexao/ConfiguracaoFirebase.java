package com.arena.biel.arenacamp.modelo.conexao;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by biel on 20/03/2018.
 */

public class ConfiguracaoFirebase {
    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;
    private static FirebaseStorage storage;
    private static StorageReference referenciaStorage;

    private ConfiguracaoFirebase(){

    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if( firebaseAuth == null ){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static DatabaseReference getFirebase(){

        if( referenciaFirebase == null ){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        return referenciaFirebase;
    }
    public static FirebaseAuth getFirebaseAuth() {

        if (firebaseAuth == null){
          inicializarFirebaseAuth();
        }
        return firebaseAuth;

    }

    private static void inicializarFirebaseAuth() {

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               if (user != null){
                   firebaseUser = user;
               }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    public static FirebaseUser getFirebaseUser(){


        return firebaseUser;
    }

    public static void logOut(){
        firebaseAuth.signOut();
    }
}
