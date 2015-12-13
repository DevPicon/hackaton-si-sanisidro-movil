package pe.apiconz.android.sanisidromovil.presentation.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import pe.apiconz.android.sanisidromovil.R;
import pe.apiconz.android.sanisidromovil.model.entities.EventEntity;
import pe.apiconz.android.sanisidromovil.presentation.adapters.ActivityAdapter;

/**
 * Created by Armando on 12/12/2015.
 */
public class CardContentTodayFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    private String TAG = CardContentTodayFragment.class.getCanonicalName();
    private ActivityAdapter adapter;



    @Override
    protected void onCreatedView() {
        List<EventEntity> list = (List<EventEntity>) getArguments().get("currentActivities");
        adapter = new ActivityAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void onCreatedView(View view) {
        super.onCreatedView(view);

        recyclerView = (RecyclerView) view;
    }


    @Override
    protected int getResourceLayout() {
        return R.layout.recycler_view;
    }


}
