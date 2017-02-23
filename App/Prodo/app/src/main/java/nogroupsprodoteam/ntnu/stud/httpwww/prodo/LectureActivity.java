package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LectureActivity extends FragmentActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String coursename = extras.getString("CourseName");
        String nickname = extras.getString("NickName");
        String lecturename = extras.getString("LectureName");
        Integer lectureID = extras.getInt("LectureID");

        TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        TextView lbl_course = (TextView) findViewById(R.id.lbl_course);
        TextView lbl_lecture = (TextView) findViewById(R.id.lbl_lecture);

        lbl_name.setText(nickname);
        lbl_course.setText(coursename);
        lbl_lecture.setText(lecturename);
        Integer numberOfLectures = Database.countLectures(lectureID);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);

        //sendMessage(coursename, nickname, lecturename, lectureID, numberOfLectures);
        //sendMessage2(coursename, nickname, lecturename, lectureID, numberOfLectures);

    }

    public void sendMessage(String course, String nickname, String lecturename, Integer lectureID, Integer numberOfLectures) {
        Intent intent = new Intent(this, PageFragment.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putString("LectureName", lecturename);
        extras.putInt("LectureID", lectureID);
        extras.putInt("NumberOfLectures", numberOfLectures);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void sendMessage2(String course, String nickname, String lecturename, Integer lectureID, Integer numberOfLectures) {
        Intent intent = new Intent(this, SwipeAdapter.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        extras.putString("LectureName", lecturename);
        extras.putInt("LectureID", lectureID);
        extras.putInt("NumberOfLectures", numberOfLectures);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
