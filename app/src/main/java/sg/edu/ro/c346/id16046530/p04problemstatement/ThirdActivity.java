package sg.edu.ro.c346.id16046530.p04problemstatement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    TextView tvID;
    EditText etTitle,etSingers,etYear;
    Button btnUpdate,btnDelete,btnCancel;
    Song data;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        tvID = findViewById(R.id.tvSongIDNum);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingerName);
        etYear = findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("Code");
        tvID.setText(""+data.getId());
        etTitle.setText(""+data.getTitle());
        etSingers.setText(""+data.getSingers());
        etYear.setText(""+data.getYear());

        int starNum = data.getStars();
        if(starNum == 1) {
            rbtn1 = findViewById(R.id.rb1);
            rbtn1.setChecked(true);
        }else if(starNum == 2) {
            rbtn2 = findViewById(R.id.rb2);
            rbtn2.setChecked(true);
        }else if(starNum == 3) {
            rbtn3 = findViewById(R.id.rb3);
            rbtn3.setChecked(true);
        }else if(starNum == 4) {
            rbtn4 = findViewById(R.id.rb4);
            rbtn4.setChecked(true);
        }else if(starNum == 5) {
            rbtn5 = findViewById(R.id.rb5);
            rbtn5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setSingers(etSingers.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                if (rbtn1.isChecked()){
                    data.setStars(1);
                }else if (rbtn2.isChecked()){
                    data.setStars(2);
                }else if (rbtn3.isChecked()){
                    data.setStars(3);
                }else if (rbtn4.isChecked()){
                    data.setStars(4);
                }else if (rbtn5.isChecked()){
                    data.setStars(5);
                }
                dbh.updateSong(data);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });
    }
}
