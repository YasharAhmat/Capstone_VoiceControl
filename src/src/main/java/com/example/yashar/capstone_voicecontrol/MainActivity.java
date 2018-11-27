package com.example.yashar.capstone_voicecontrol;

import android.content.ActivityNotFoundException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button openMic;
    private TextView Des;
    private final int REQ_CODE_SPEECH_OUT = 3000;
    private final String mQ = "Please enter the destination";
    //private final String voiceinput = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openMic = (Button) findViewById(R.id.voice_button);
        Des = (TextView) findViewById(R.id.des);

        openMic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startSpeechRecognizer();
            }
        });

    }

    private void startSpeechRecognizer(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, mQ);
        startActivityForResult(intent, REQ_CODE_SPEECH_OUT);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == REQ_CODE_SPEECH_OUT){
            if(resultCode == RESULT_OK && null != data){
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Des.setText(results.get(0));
            }

        }
    }

}
