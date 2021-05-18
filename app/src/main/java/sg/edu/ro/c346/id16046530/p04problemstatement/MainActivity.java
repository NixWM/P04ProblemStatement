package sg.edu.ro.c346.id16046530.p04problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Create the DBHelper object, passing in the activity's Context

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Test", "DONE");
                startActivity(intent);
            }
        });
    }
}