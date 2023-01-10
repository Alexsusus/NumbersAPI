package com.example.numbersapi.screens;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.numbersapi.App;
import com.example.numbersapi.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    EditText fieldNumbers;
    Button factNumber, factRandomNumber;
    TextView firstFactFromEnd, secondFactFromEnd, thirdFactFromEnd, fourthFactFromEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        factRandomNumber = findViewById(R.id.buttonGetRandom);

        fieldNumbers = findViewById(R.id.fieldNumbers);
        factNumber = findViewById(R.id.buttonGetFact);


        firstFactFromEnd = findViewById(R.id.firstFactFromEnd);
        secondFactFromEnd = findViewById(R.id.secondFactFromEnd2);
        thirdFactFromEnd = findViewById(R.id.thirdFactFromEnd);
        fourthFactFromEnd = findViewById(R.id.fourthFactFromEnd);

        Disposable subscribe = App.getInstance().getSqDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchQueries -> {

                    int size = searchQueries.size();

                    if (size > 0) {
                        firstFactFromEnd.setText(searchQueries.get(size - 1).fact);
                    }
                    if (size > 1)
                        secondFactFromEnd.setText(searchQueries.get(size - 2).fact);
                    if (size > 2)
                        thirdFactFromEnd.setText(searchQueries.get(size - 3).fact);
                    if (size > 3)
                        fourthFactFromEnd.setText(searchQueries.get(size - 4).fact);

                });

        firstFactFromEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickText(firstFactFromEnd.getText().toString());
            }
        });
        secondFactFromEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickText(secondFactFromEnd.getText().toString());
            }
        });
        thirdFactFromEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickText(thirdFactFromEnd.getText().toString());
            }
        });
        fourthFactFromEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickText(fourthFactFromEnd.getText().toString());
            }
        });


    }

    public void toSecondPageExtra(View view) { //With number
        String number = fieldNumbers.getText().toString();
        if (number.equals("")) {
            Toast.makeText(this, "You didn't enter a number. Try again", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("newFact", number);
            startActivity(intent);
        }
    }

    public void toSecondPage(View view) { //Random
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void clickText(String fact) { //Send fact
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("oldFact", fact);
        startActivity(intent);
    }

}