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


        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        String topic = "emne";
        bundle.putString("topic",topic);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        Integer dataFromActivity = LectureActivity.getNumberOfLectures();
        return dataFromActivity;
    }
}
