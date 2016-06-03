package br.com.cemobile.twitchtop50app;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import junit.framework.Assert;

import br.com.cemobile.twitchtop50app.controller.activity.GameActivity_;
import br.com.cemobile.twitchtop50app.controller.activity.MainActivity_;
import br.com.cemobile.twitchtop50app.model.Game;
import br.com.cemobile.twitchtop50app.model.GameLogo;
import br.com.cemobile.twitchtop50app.model.TopGame;

/**
 * Created by celso on 31/05/16.
 */
public class MainActivityRobotiumTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    private Solo solo;

    public MainActivityRobotiumTest() {
        super(MainActivity_.class);
    }

    public void setUp() throws Exception {
        this.solo = new Solo(this.getInstrumentation(), this.getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        this.solo.finishOpenedActivities();
    }

    public void test1WrongActivity() throws Exception {
        this.solo.assertCurrentActivity("wrong activity", MainActivity_.class);
    }

    public void test2GoToGameActivity() throws Exception {

        Condition condition = new Condition() {
            @Override
            public boolean isSatisfied() {
                View progressBar = solo.getView(R.id.progressBar);
                return progressBar.getVisibility() == View.GONE;
            }
        };

        this.solo.waitForCondition(condition, 20000);

        this.solo.scrollDown();

        this.solo.clickInRecyclerView(1);

        Assert.assertTrue(this.solo.waitForActivity(GameActivity_.class, 10000));
    }

    public void test3ExitByMenu() throws Exception {
        this.solo.sendKey(Solo.MENU);

        Assert.assertTrue(this.solo.waitForText(this.solo.getString(R.string.action_exit)));
    }
}
