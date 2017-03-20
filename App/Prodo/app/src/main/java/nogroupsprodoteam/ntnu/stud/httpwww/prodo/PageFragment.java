package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    TextView textView;
    TextView testShowRating;
    TextView ratingDescription;
    String staus;
    RatingBar ratingBar;
    View view;
    Integer topicID;
    Button submitQuestionButton;
    TextView textQuestion;
    EditText question;
    String questionString;
    String prevquestionString;

    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.page_fragment, container, false);
        textView = (TextView)view.findViewById(R.id.lbl_page);
        Bundle bundle = getArguments();
        //gets values from adapter
        String topic = bundle.getString("topic");
        topicID = bundle.getInt("topicID");
        textView.setText(topic);

        //currently unused and unreachable code for getting values from LectureActivity
        //use SendMessage in LectureActivity first
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        String coursename = extras.getString("CourseName");
        String nickname = extras.getString("NickName");
        String lecturename = extras.getString("LectureName");
        Integer lectureID = extras.getInt("LectureID");
        Integer numberOfLectures = extras.getInt("NumberOfLectures");



        testShowRating = (TextView)view.findViewById(R.id.lbl_testRatingView);
        testShowRating.setText("No rating yet..");

        ratingDescription = (TextView)view.findViewById(R.id.lbl_ratingDescription);
        ratingDescription.setText("How well do you understand the current topic?");

        textQuestion = (TextView)view.findViewById(R.id.lbl_askQuestion);
        textQuestion.setText("Do you have any questions?");
        submitQuestionButton = (Button)view.findViewById(R.id.btn_SubmitQuestion);
        question = (EditText)view.findViewById(R.id.txt_question);
        question.setHint("Ask a question");


        //Listening for changes in rating

        addListenerOnRatingBar();

        //Listening for changes in EditText field
        submitQuestionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               questionString = question.getText().toString();
               //lag stringlength validation og ikkje likt originaltext
               if (isQuestionValid(questionString)){
                   question.setText(null);
                   String errorMessage = Database.sendQuestion(topicID,questionString);
                   testShowRating.setText(errorMessage);
               }else{
                   question.setText(null);
               }

           }
        });


        return view;
    }

    private void addListenerOnRatingBar() {
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_understanding);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //showing rating value in testtext display
                staus = Integer.toString(Math.round(rating));

                String errorMessage = Database.setRating(topicID,Math.round(rating));
                testShowRating.setText(errorMessage);
            }
        });

    }

   // private void addOnClicK

    private boolean isQuestionValid(String questionString){
        if (questionString.length()<2){
            question.setHint("Invalid question, try again.");
            return false;
        }

        question.setHint("Ask a new question");

        return true;
    }




}
