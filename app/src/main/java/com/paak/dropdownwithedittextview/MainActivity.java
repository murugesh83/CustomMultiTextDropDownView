package com.paak.dropdownwithedittextview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    String[] languages = { "C","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON" };

    String previousText ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, languages);
        //Find TextView control
        MultiAutoCompleteTextView acTextView = (MultiAutoCompleteTextView) findViewById(R.id.languages);
        adapter.setNotifyOnChange(true);
        acTextView.setAdapter(adapter);
        acTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //acTextView.showDropDown();
        acTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = null;
                in   = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.showSoftInput(acTextView, InputMethodManager.SHOW_IMPLICIT);
                acTextView.showDropDown();
                acTextView.requestFocus();
                return true;
            }
        });
        acTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("MainActivity", "muru focused");
                //acTextView = (MultiAutoCompleteTextView) v;
            }
        });

        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "muru onItemClicked");
            }
        });
    }
}