package br.com.cemobile.twitchtop50app.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.cemobile.twitchtop50app.Constant;
import br.com.cemobile.twitchtop50app.R;
import br.com.cemobile.twitchtop50app.controller.activity.GameActivity_;
import br.com.cemobile.twitchtop50app.model.TopGame;
import br.com.cemobile.twitchtop50app.view.transformation.CircleTransformation;

/**
 * Created by celso on 29/05/16.
 */
@EViewGroup(R.layout.list_game_item)
public class GameItemView extends CardView implements View.OnClickListener {

    @ViewById
    protected ImageView contentImageView;

    @ViewById
    protected TextView nameTextView;

    @ViewById
    protected TextView positionTextView;

    private TopGame topGame;

    public GameItemView(Context context) {
        super(context);
        this.setOnClickListener(this);
    }

    public void bind(final TopGame topGame) {
        this.topGame = topGame;
        final Resources resources = this.getContext().getResources();

        Glide.clear(contentImageView);

        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(this.getContext()).load(topGame.getGame().getBox().getSmall());

        drawableTypeRequest.placeholder(R.mipmap.ic_placeholder)
                .override(Constant.GAME_BOX_MEDIUM_WIDTH, Constant.GAME_BOX_MEDIUM_HEIGHT)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .animate(android.R.anim.fade_in)
                .bitmapTransform(new CircleTransformation(this.getContext()))
                .into(this.contentImageView);

        this.contentImageView.setOnClickListener(this);

        this.nameTextView.setText(this.topGame.getGame().getName());

        this.positionTextView.setText(String.format(Constant.POSITION, this.topGame.getPosition()));
    }

    @Override
    public void onClick(View v) {
        Intent goToGameActivty = new Intent(this.getContext(), GameActivity_.class);
        goToGameActivty.putExtra(Constant.GAME_INFORMATION, this.topGame);
        this.getContext().startActivity(goToGameActivty);
    }
}
