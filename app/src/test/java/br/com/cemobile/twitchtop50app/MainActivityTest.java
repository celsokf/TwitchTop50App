package br.com.cemobile.twitchtop50app;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import br.com.cemobile.twitchtop50app.controller.activity.MainActivity_;
import br.com.cemobile.twitchtop50app.controller.fragment.MainFragment_;
import de.greenrobot.event.EventBus;

/**
 * Created by celso on 31/05/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    public MainActivityTest() {
        super(MainActivity_.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(false);
    }

    @Test
    public void testSimpleConditions() {
        MainActivity_ activity = this.getActivity();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        assertTrue(EventBus.getDefault().isRegistered(activity));

        assertNotNull(fragmentManager.getFragments());
        assertNotNull(fragmentManager.getFragments().size() == 1);
        assertNotNull(fragmentManager.getFragments().get(0) instanceof MainFragment_);

        MainFragment_ fragment = (MainFragment_) fragmentManager.getFragments().get(0);
        assertNotNull(fragment);

        RecyclerView gameRecyclerView = (RecyclerView) fragment.findViewById(R.id.gamesRecyclerView);
        assertNotNull(gameRecyclerView);

        assertTrue(gameRecyclerView.getChildCount() > 0);
    }

}
