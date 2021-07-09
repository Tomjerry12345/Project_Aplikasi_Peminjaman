package com.example.loser.tugasvoice.Login_Register;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class App  extends Application {

    private DatabaseReference userDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentOnlineUser;
    Context context;

    @Override
    public void onCreate() {

        super.onCreate();

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //  all images >> load offline
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso builtPicasso = builder.build();
        builtPicasso.setIndicatorsEnabled(true);
        builtPicasso.setLoggingEnabled(true);

        Picasso.setSingletonInstance(builtPicasso);


    }
}
