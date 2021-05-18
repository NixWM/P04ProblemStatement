package sg.edu.ro.c346.id16046530.p04problemstatement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    CustomAdapter adapter;
    ArrayList<Song> al;
    Button btn5star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lv = (ListView) this.findViewById(R.id.lv);
        btn5star = findViewById(R.id.btn5star);

        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> Songs = dbh.getAllNotes();

        adapter = new CustomAdapter(this, R.layout.second_row, Songs);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("Code", al.get(position).getId());
                startActivityForResult(intent, 9);
            }
        });

        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Song> Songs = dbh.getAllNotes();
                adapter = new CustomAdapter(SecondActivity.this, R.layout.second_row, Songs);
                lv.setAdapter(adapter);
            }
        });


    }
}
