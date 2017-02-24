package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        //pulls array with courses from database
        ArrayList<String> spinnerArray = Database.getCourses();
        //creates spinner and loads it with the array provided
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_course);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        //collects values from MainActivity
        Intent intent = getIntent();
        final String nickname = intent.getStringExtra("NickName");

        final TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        lbl_name.setText(nickname);

        Button btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //gets selected course
                final String course = spinner.getSelectedItem().toString();
                final Integer courseNumber = spinner.getSelectedItemPosition() + 1;
                sendMessage(course, nickname, courseNumber);
            }
        });
    }
    //Sends values to and opens CourseActivity
    public void sendMessage(String course, String nickname, Integer courseNumber) {
        Intent intent = new Intent(this, CourseActivity.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putInt("CourseNumber", courseNumber);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
