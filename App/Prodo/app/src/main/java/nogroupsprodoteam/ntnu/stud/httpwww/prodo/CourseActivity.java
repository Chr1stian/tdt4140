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

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String coursename = extras.getString("CourseName");
        final String nickname = extras.getString("NickName");

        TextView lbl_course = (TextView) findViewById(R.id.lbl_course);
        lbl_course.setText(coursename);

        TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        lbl_name.setText(nickname);


        final ListView list = (ListView) findViewById(R.id.list_lecture);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lectures_array, android.R.layout.simple_selectable_list_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        list.setAdapter(adapter);

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
