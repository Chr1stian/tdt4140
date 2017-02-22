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

    public final static String CourseName = "";
    public final static String NickName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        ArrayList<String> spinnerArray = Database.courseCodes();
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_course);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView lbl_name = (TextView) findViewById(R.id.lbl_name);
        lbl_name.setText(message);

        Button btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String course = spinner.getSelectedItem().toString();
                sendMessage(course, message);
            }
        });
    }

    public void sendMessage(String course, String nickname) {
        Intent intent = new Intent(this, CourseActivity.class);
        Bundle extras = new Bundle();

        extras.putString("CourseName", course);
        extras.putString("NickName", nickname);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
