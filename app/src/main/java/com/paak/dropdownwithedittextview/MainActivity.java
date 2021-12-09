package com.paak.dropdownwithedittextview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Spinner;

import viewmodel.MyDataViewModel;

public class MainActivity extends AppCompatActivity {

    MultiAutoCompleteTextView acTextView;
    MyDataViewModel viewModel;
    Spinner spinner;
    ArrayAdapter<String> countyAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acTextView = (MultiAutoCompleteTextView) findViewById(R.id.languages);
        spinner = (Spinner) findViewById(R.id.planets_spinner);

         viewModel = new ViewModelProvider(this).get(MyDataViewModel.class);
          viewModel.getShoppingList().observe(this, shoppingList -> {
              ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shoppingList);
            //Find TextView control
            adapter.setNotifyOnChange(true);
            acTextView.setAdapter(adapter);
            acTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        });


       viewModel.getCounty_id().observe(this, s -> {
           Log.d("MainActivity", "muru "+s);
           int spinnerPosition = countyAdapter.getPosition(s);
           spinner.setSelection(spinnerPosition);
           countyAdapter.setNotifyOnChange(true);

       });

       //viewModel = new ViewModelProvider(this).get(MyDataViewModel.class);
       viewModel.getShoppingList().observe(this, shoppingList -> {
           countyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shoppingList);
            spinner.setAdapter(countyAdapter);
           countyAdapter.setNotifyOnChange(true);
        });

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
    }
}