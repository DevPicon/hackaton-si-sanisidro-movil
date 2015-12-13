package pe.apiconz.android.sanisidromovil.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.apiconz.android.sanisidromovil.R;
import pe.apiconz.android.sanisidromovil.model.entities.EventEntity;
import pe.apiconz.android.sanisidromovil.utils.Utils;

/**
 * Created by Armando on 12/12/2015.
 */
public class ActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EventEntity> eventList;

    public ActivityAdapter(List<EventEntity> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event_item, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ActivityViewHolder activityViewHolder = (ActivityViewHolder) holder;
        EventEntity eventEntity = eventList.get(position);
        activityViewHolder.getCardTitle().setText(eventEntity.getNombre());
        activityViewHolder.getCardText().setText(Utils.formatDateTime(eventEntity));
    }


    @Override
    public int getItemCount() {
        if (eventList != null) {
            return eventList.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        EventEntity eventEntity = eventList.get(position);
        return eventEntity.getId();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
