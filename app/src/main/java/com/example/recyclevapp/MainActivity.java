package com.example.recyclevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataSet; // הרשימה המקורית
    private RecyclerView recyclerView;
    private CustomeAdapter adapter;
    private EditText searchEditText; // שדה החיפוש

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // אתחול רכיבים
        dataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.resView);
        searchEditText = findViewById(R.id.searchEditText); // שדה החיפוש ב-XML

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // מילוי הרשימה
        for (int i = 0; i < myData.nameArray.length; i++) {
            dataSet.add(new DataModel(
                    myData.nameArray[i],
                    myData.descriptionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        // אתחול Adapter עם הרשימה המקורית
        adapter = new CustomeAdapter(this, new ArrayList<>(dataSet));
        recyclerView.setAdapter(adapter);

        // הוספת מאזין לשדה החיפוש
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString()); // פילטור לפי הטקסט
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void filter(String text) {
        ArrayList<DataModel> filteredList = new ArrayList<>();
        for (DataModel item : dataSet) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList); // מעדכן את ה-Adapter עם הרשימה המסוננת
    }
}
