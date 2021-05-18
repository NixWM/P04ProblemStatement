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

    Button btn5star;
    ListView lv;
    ArrayList<Song> al, newal;
    ArrayList<Integer> spinneral;
    CustomAdapter adapter;
    ArrayAdapter<Integer> spinneraa;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        btn5star = (Button) findViewById(R.id.btn5star);
        lv = (ListView) findViewById(R.id.lv);
        spinner = (Spinner) findViewById(R.id.spinner);

        DBHelper dbh = new DBHelper(SecondActivity.this);
        al = dbh.getAllNotes();
        newal = al;
        adapter = new CustomAdapter(this, R.layout.second_row, al);
        lv.setAdapter(adapter);

        spinneral = new ArrayList<>();
        for ( Song i: al) {
            spinneral.add(i.getYear());
        }
        spinneraa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneral);
        spinneraa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneraa);

        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al = dbh.getAllNotes();
                newal.clear();
                for (Song i: al) {
                    if (i.getStars() == 5)
                        newal.add(i);
                }
                adapter = new CustomAdapter(SecondActivity.this, R.layout.second_row, newal);
                lv.setAdapter(adapter);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Code", newal.get(position));
                startActivityForResult(i, 9);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllNotes();
                int selected = spinneral.get(position);
                newal.clear();
                for (Song i: al) {
                    if (i.getYear() == selected)
                        newal.add(i);
                }
                adapter = new CustomAdapter(SecondActivity.this, R.layout.second_row, newal);
                lv.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
