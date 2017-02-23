package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    TextView textView;
    TextView testShowRating;
    String staus;
    RatingBar ratingBar;

    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.page_fragment, container, false);
        textView = (TextView)view.findViewById(R.id.lbl_page);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        textView.setText("Page number " + message);

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        String coursename = extras.getString("CourseName");
        String nickname = extras.getString("NickName");
        String lecturename = extras.getString("LectureName");
        Integer lectureID = extras.getInt("LectureID");
        Integer numberOfLectures = extras.getInt("NumberOfLectures");


        ratingBar = (RatingBar)view.findViewById(R.id.ratingBar_understanding);
        ratingBar.setOnClickListener(ratingUpdate);



       /* staus = Integer.toString(Math.round(ratingBar.getRating()));

        testShowRating = (TextView)view.findViewById(R.id.lbl_testRatingView);
        testShowRating.setText(staus);
*/

        return view;
    }

    public View.OnClickListener ratingUpdate = new View.OnClickListener() {
        public void onClick(View view) {

            RatingBar barUpdate = (RatingBar) view;
            testShowRating = (TextView)view.findViewById(R.id.lbl_testRatingView);
            staus = Integer.toString(Math.round(barUpdate.getRating()));
            testShowRating.setText(staus);
        }
    };

}
