package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.database.SQLException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.*;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {
    Database db = new Database();
    EditText txt_name;
    public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txt_name = (EditText) findViewById(R.id.txt_name);


        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                sendMessage();
                //txt_name.setText(db.test());
            }
        });

    }

    public void sendMessage() {
        Intent intent = new Intent(this, SelectionActivity.class);
        EditText editText = (EditText) findViewById(R.id.txt_name);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}
