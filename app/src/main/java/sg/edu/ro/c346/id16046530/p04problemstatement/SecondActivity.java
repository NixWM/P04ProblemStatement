package sg.edu.ro.c346.id16046530.p04problemstatement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    CustomAdapter adapter;
    ArrayList<Song> al5Star, alYear;
    ArrayList<Integer> al;
    Button btn5star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lv = (ListView) this.findViewById(R.id.lv);
        btn5star = findViewById(R.id.btn5star);
        al5Star = new ArrayList<>();
        alYear = new ArrayList<>();
        al = new ArrayList<>();

        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> Songs = dbh.getAllNotes();

        adapter = new CustomAdapter(this, R.layout.second_row, Songs);
        lv.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        for (int i = 0; i < Songs.size(); i++) {
            int year = Songs.get(i).getYear();
            al.add(year);
        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,al);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                alYear.clear();
                for (Song i : Songs) {
                    if (i.getYear() == (int)spinner.getSelectedItem()) {
                        alYear.add(i);
                    }
                }
                lv.setAdapter(null);
                adapter = new CustomAdapter(SecondActivity.this, R.layout.second_row, alYear);
                lv.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("Code", Songs.get(position));
                startActivityForResult(intent, 9);
            }
        });

        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al5Star.clear();
                for (Song i : Songs) {
                    if (i.getStars() == 5) {
                        al5Star.add(i);
                    }
                }
                lv.setAdapter(null);
                adapter = new CustomAdapter(SecondActivity.this, R.layout.second_row, al5Star);
                lv.setAdapter(adapter);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> Songs = dbh.getAllNotes();
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            lv.setAdapter(null);
            adapter = new CustomAdapter(this, R.layout.second_row, Songs);
            lv.setAdapter(adapter);
        }
    }
}
