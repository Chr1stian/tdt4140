package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String coursename = extras.getString("CourseName");
        final String nickname = extras.getString("NickName");
        final Integer courseNumber = extras.getInt("CourseNumber");

        TextView lbl_course = (TextView) findViewById(R.id.lbl_course);
        lbl_course.setText(coursename);

        TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        lbl_name.setText(nickname);

        ArrayList<String> ListViewArray = Database.lectures(courseNumber);

        final ListView list = (ListView) findViewById(R.id.list_lecture);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, ListViewArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object lecture = list.getItemAtPosition(position);
                sendMessage(coursename, nickname, lecture.toString());
            }
        });
    }

    public void sendMessage(String course, String nickname, String lecture) {
        Intent intent = new Intent(this, LectureActivity.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putString("LectureName", lecture);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
