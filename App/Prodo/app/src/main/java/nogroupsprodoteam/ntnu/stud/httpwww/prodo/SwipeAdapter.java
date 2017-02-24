package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Christian on 21.02.2017.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {




    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //creates fragment
        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        //gets topicID and topic that corresponds to the current number of swipable fragment
        Integer topicID = Database.getTopicID(position+1, LectureActivity.getLectureID());
        String topic = Database.getTopic(position+1, LectureActivity.getLectureID());
        bundle.putString("topic",topic);
        bundle.putInt("topicID", topicID);
        //sends bundle/values to fragment for display
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        //gets number of topics for selected lecture and sets swipeable fragments to its count
        Integer dataFromActivity = LectureActivity.getNumberOfTopics();
        return dataFromActivity;
    }
}
