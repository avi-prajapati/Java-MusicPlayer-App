package com.example.isangeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView BottomNavigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView=findViewById(R.id.BottomNavigationView);


        BottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId= item.getItemId();

                if(itemId==R.id.home) {
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, Home_Fragment.class,null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                }

                else if(itemId==R.id.song) {
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, Song_Fragment.class,null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                }

                else if(itemId==R.id.fav) {
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, Favourite_Fragment.class,null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                }

                else if(itemId==R.id.playlist) {
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, Playlist_Fragment.class,null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name")
                            .commit();
                }

                return true;
            }
        });




    }


}