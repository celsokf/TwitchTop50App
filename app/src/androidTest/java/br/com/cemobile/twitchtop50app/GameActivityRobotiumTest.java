package br.com.cemobile.twitchtop50app;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import junit.framework.Assert;

import br.com.cemobile.twitchtop50app.controller.activity.GameActivity_;
import br.com.cemobile.twitchtop50app.controller.activity.MainActivity;
import br.com.cemobile.twitchtop50app.model.Game;
import br.com.cemobile.twitchtop50app.model.GameLogo;
import br.com.cemobile.twitchtop50app.model.TopGame;

/**
 * Created by celso on 31/05/16.
 */
public class GameActivityRobotiumTest extends ActivityInstrumentationTestCase2<GameActivity_> {

    private Solo solo;

    public GameActivityRobotiumTest() {
        super(GameActivity_.class);
    }

    public void setUp() throws Exception {
        this.solo = new Solo(this.getInstrumentation(), this.getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        this.solo.finishOpenedActivities();
    }

    public void test1GameActivity() throws Exception {
        this.solo.assertCurrentActivity("wrong activity", GameActivity_.class);
    }

}
