package com.example.isangeet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;


public class Song_Fragment extends Fragment {
    ListView song_listview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_song_, container, false);

        song_listview=view.findViewById(R.id.song_listview);
        Fetch_Song fetch =new Fetch_Song();

        Dexter.withContext(getContext())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        ArrayList<File> songs=fetchSong(Environment.getExternalStorageDirectory());
                        String[] items=new String[songs.size()];
                        for(int i=0;i<songs.size();i++) {
                            items[i]=songs.get(i).getName().replace(".mp3","");
                        }

                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,items);
                        song_listview.setAdapter(adapter);

                        song_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent =new Intent(getContext(), PlayingSong.class);
                                startActivity(intent);
                            }
                        });


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {


                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();

        return view;
    }


    public ArrayList<File> fetchSong(File file) {
        ArrayList arrayList=new ArrayList();
        File [] songs=file.listFiles();
        if(songs!=null) {
            for(File f:songs) {
                if (!f.isHidden() && f.isDirectory()) {
                    arrayList.addAll(fetchSong(f));
                }
                else {
                    if (f.getName().endsWith(".mp3") && !f.getName().startsWith(".")) {
                        arrayList.add(f);
                    }
                }
            }
        }



        return arrayList;

    }


}