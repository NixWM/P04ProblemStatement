package sg.edu.ro.c346.id16046530.p04problemstatement;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lv = (ListView) this.findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> Songs = dbh.getAllNotes();

        adapter = new CustomAdapter(this, R.layout.second_row, Songs);
        lv.setAdapter(adapter);
    }
}
