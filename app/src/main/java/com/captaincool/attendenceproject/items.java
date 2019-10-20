package com.captaincool.attendenceproject;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class items {
    private String tx;
//    private RadioGroup rg;
//    private RadioButton rb1;
//    private RadioButton rb2;
    items(String text,RadioButton rbtn1,RadioButton rbtn2)
    {
        tx = text;
//        rb1 = rbtn1;
//        rb2 = rbtn2;
    }

    public String getText()
    {
        return tx;
    }

}
