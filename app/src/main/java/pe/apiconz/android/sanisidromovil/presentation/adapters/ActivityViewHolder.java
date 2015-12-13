package pe.apiconz.android.sanisidromovil.presentation.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pe.apiconz.android.sanisidromovil.R;
import pe.apiconz.android.sanisidromovil.presentation.activities.DetailActivity;

/**
 * Created by Armando on 12/12/2015.
 */
public class ActivityViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.card_image)
    protected ImageView cardImage;
    @Bind(R.id.card_title)
    protected TextView cardTitle;
    @Bind(R.id.card_text)
    protected TextView cardText;
    @Bind(R.id.action_button)
    protected Button actionButton;
    @Bind(R.id.share_button)
    protected ImageButton shareButton;
    @Bind(R.id.add_button)
    protected ImageButton scheduleButton;


    public ActivityViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("id",v.getId());
                intent.putExtra("itemId",getItemId());
                v.getContext().startActivity(intent);
            }
        });
    }

    public Button getActionButton() {
        return actionButton;
    }

    public void setActionButton(Button actionButton) {
        this.actionButton = actionButton;
    }

    public ImageView getCardImage() {
        return cardImage;
    }

    public void setCardImage(ImageView cardImage) {
        this.cardImage = cardImage;
    }

    public TextView getCardText() {
        return cardText;
    }

    public void setCardText(TextView cardText) {
        this.cardText = cardText;
    }

    public TextView getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(TextView cardTitle) {
        this.cardTitle = cardTitle;
    }

    public ImageButton getScheduleButton() {
        return scheduleButton;
    }

    public void setScheduleButton(ImageButton scheduleButton) {
        this.scheduleButton = scheduleButton;
    }

    public ImageButton getShareButton() {
        return shareButton;
    }

    public void setShareButton(ImageButton shareButton) {
        this.shareButton = shareButton;
    }
}
