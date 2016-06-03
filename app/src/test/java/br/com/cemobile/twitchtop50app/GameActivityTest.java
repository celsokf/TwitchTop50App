package br.com.cemobile.twitchtop50app;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Test;

import br.com.cemobile.twitchtop50app.controller.activity.GameActivity;
import br.com.cemobile.twitchtop50app.controller.activity.GameActivity_;
import de.greenrobot.event.EventBus;

/**
 * Created by celso on 31/05/16.
 */
public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity_> {

    public GameActivityTest() {
        super(GameActivity_.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(false);
    }

    @Test
    public void testSimpleConditions() {
        GameActivity_ activity = this.getActivity();

        ImageView contentImageView = (ImageView) activity.findViewById(R.id.contentImageView);
        assertNotNull(contentImageView);

        TextView nameTextView = (TextView) activity.findViewById(R.id.nameTextView);
        assertNotNull(nameTextView);

        TextView channelTextView = (TextView) activity.findViewById(R.id.channelTextView);
        assertNotNull(channelTextView);

        TextView viewerTextView = (TextView) activity.findViewById(R.id.viewerTextView);
        assertNotNull(viewerTextView);
    }
}
