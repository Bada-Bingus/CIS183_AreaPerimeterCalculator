package com.example.cis183_areaperimetercalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //rectangle & square
    // area = length & width
    // perimeter = length & width

    //circle
    //area = radius
    //circumference = radius

    //triangle
    //area = base & height
    //perimeter = side a, side b, side c
    EditText et_j_length;
    EditText et_j_width;
    ConstraintLayout cont_j_squareRectView;
    Spinner sp_j_shapes;
    TextView tv_j_results;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_j_length = findViewById(R.id.et_v_length);
        et_j_width = findViewById(R.id.et_v_width);
        cont_j_squareRectView = findViewById(R.id.cont_v_squareRectangle);
        sp_j_shapes = findViewById(R.id.sp_v_shapes);
        tv_j_results = findViewById(R.id.tv_v_results);

        // Because we want to make a simple drop down menu (spinner) that will only contain
        // strings as options, we can use a string array with the built-in array adaptor
        // to populate the spinner.


        // We will use this to populate our spinner
        String[] shapes = new String[]{"Square", "Rectangle", "Circle", "Triangle"};

        //adaptor is what links the java code with the xml for the spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,shapes);

        sp_j_shapes.setAdapter(spinnerAdapter);

        setupSpinnerChangeListener();
        textChangeListenerSquareRect();
    }

    /// /////////////////////////////////////////////////////
    /// /LISTENERS
    /// /////////////////////////////////////////////////////
    public void setupSpinnerChangeListener()
    {
       sp_j_shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // 0-3 Indexes (square, rectangle, circle, triangle)
               //square and rectangle will be mostly the same
               if(position == 0) //square
               {

                   cont_j_squareRectView.setVisibility(View.VISIBLE);

               }
               else if(position == 1) //rectangle
               {

                   cont_j_squareRectView.setVisibility(View.VISIBLE);

               }
               else if(position == 2) //circle
               {

                   hideConstraintView(cont_j_squareRectView);

               }
               else if(position == 3) //triangle
               {

                   hideConstraintView(cont_j_squareRectView);

               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }

    public void hideConstraintView(ConstraintLayout cl)
    {
        cl.setVisibility(View.INVISIBLE);
    }

    public void textChangeListenerSquareRect()
    {
        et_j_width.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                setAreaAndPerimeterSquareRec(et_j_length.getText().toString(), et_j_width.getText().toString());

            }
        });

        et_j_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                setAreaAndPerimeterSquareRec(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });
    }

    public void setAreaAndPerimeterSquareRec(String lengthS, String widthS)
    {
        if(lengthS.isEmpty() || widthS.isEmpty())
        {
            //if either is empty, it wont compute anything, so the app doesn't break
            return;
        }

        //converts the strings to a double
        double length = Integer.parseInt(lengthS);
        double width = Integer.parseInt(widthS);

        double area = length * width;
        double perimeter = length + length + width + width;

        tv_j_results.setText("Area = " + area + "\n Perimeter = " + perimeter);

    }

}