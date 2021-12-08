package com.paak.dropdownwithedittextview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

public class CustomMultiAutoCompleteTextView extends androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView {

    public CustomMultiAutoCompleteTextView(Context context) {
        super(context);
    }

    public CustomMultiAutoCompleteTextView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public CustomMultiAutoCompleteTextView(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void replaceText(CharSequence text) {
        Log.d("InstantAutoComplete", "muru replaceText 1"+text);
        if(!getText().toString().contains(text+", ")) {
            super.replaceText(text);
        }
            Log.d("InstantAutoComplete", "muru replaceText " + getText());

    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        super.performFiltering(text, keyCode);
        Log.d("InstantAutoComplete", "muru performFiltering "+text);
    }
}
