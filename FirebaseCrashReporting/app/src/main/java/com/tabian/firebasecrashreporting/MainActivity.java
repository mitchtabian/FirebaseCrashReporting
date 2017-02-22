package com.tabian.firebasecrashreporting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btnError1, btnError2, btnError3;

    private EditText mText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnError1 = (Button) findViewById(R.id.btnError1);
        btnError2 = (Button) findViewById(R.id.btnError2);
        btnError3 = (Button) findViewById(R.id.btnError3);
        mText1 = (EditText) findViewById(R.id.editText);

        Log.d(TAG, "onCreate: starting.");
        FirebaseCrash.log("onCreate started.");

        btnError1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseCrash.log("btnError1 Clicked.");
                //this will throw an exception and report it
                String text = null;
                mText1.setText(text.toString());
            }
        });

        btnError2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseCrash.log("btnError2 Clicked.");
                String filepath = "sdcard/made-up/filepath/";
                try{
                    File file = new File(filepath);
                    InputStream inputStream = new FileInputStream(file);
                    inputStream.read();
                }catch (FileNotFoundException e){
                    FirebaseCrash.report(new Exception(
                            "FileNotFoundException in btnError2. Probably the filepath:" + filepath
                    ));
                } catch (IOException e) {
                    FirebaseCrash.report(new Exception(
                            e.toString()
                    ));
                }
            }
        });

        btnError3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnError3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseCrash.log("btnError3 Clicked.");
                        ArrayList<String> theList = new ArrayList<>();
                        theList.add("String 1");
                        theList.add("String 2");
                        theList.add("String 3");

                        //this will throw an exception because the index is out of bounds
                        for (int i = 0; i <= theList.size(); i++){
                            Log.d(TAG, "onClick: theList: " + theList.get(i));
                        }
                    }
                });
            }
        });
    }
}




















