package com.example.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        if(savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("TextToBundle"));
        }
        Log.d("Lifecycle method: ", "OnCreate()");
        textView.append("OnCreate()" + "\n");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Lifecycle method: ", "onStart()");
        textView.append("onStart()" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Lifecycle method: ", "onResume()");
        textView.append("onResume()" + "\n");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("Lifecycle method: ", "onPause()");
        textView.append("onPause()" + "\n");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Lifecycle method: ", "onStop()");
        textView.append("onStop()" + "\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Lifecycle method: ", "onDestroy()");
        textView.append("onDestroy()" + "\n");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("onSaveInstanceState: ", "onSaveInstanceState()");
        textView.append("onSaveInstanceState()" + "\n");

        outState.putString("TextToBundle", textView.getText().toString());
    }
}