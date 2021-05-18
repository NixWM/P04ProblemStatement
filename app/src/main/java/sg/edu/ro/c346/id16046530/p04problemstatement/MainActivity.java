package sg.edu.ro.c346.id16046530.p04problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShowList;
    RadioButton rbtnStars;
    RadioGroup rgStar;


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
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                String year = etYear.getText().toString();
                rgStar = findViewById(R.id.rgStars);

                // returns as Integer for (year)
                int songYear = Integer.parseInt(year);
                int selectedStar = rgStar.getCheckedRadioButtonId();
                rbtnStars =  findViewById(selectedStar);
                // returns the relevant Number Object holding the value of the argument passed
                String num = String.valueOf(rbtnStars.getText());
                int numStar = Integer.valueOf(num);

                // Create the DBHelper object, passing in the activity's Context
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_song = dbh.insertSong(title,singer,songYear,numStar);
                dbh.close();
                
                if (inserted_song != -1){
                    Toast.makeText(MainActivity.this, "Successfully inserted.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}