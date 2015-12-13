package pe.apiconz.android.sanisidromovil.presentation.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pe.apiconz.android.sanisidromovil.R;
import pe.apiconz.android.sanisidromovil.model.entities.EventEntity;
import pe.apiconz.android.sanisidromovil.presentation.adapters.ActivityAdapter;

/**
 * Created by Armando on 12/12/2015.
 */
public class CardContentFragment extends BaseFragment {

    protected RecyclerView recyclerView;

    private ActivityAdapter adapter;

    private List<EventEntity> eventList;

    @Override
    protected void onCreatedView() {

    }

    @Override
    protected void onCreatedView(View view) {
        super.onCreatedView(view);
        iniData();
        recyclerView = (RecyclerView) view;
        adapter = new ActivityAdapter(eventList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void iniData() {

        eventList = new ArrayList<>();

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(123);
        eventEntity.setNombre("Nombre ficticio");
        eventEntity.setFecha("12/12/2015");
        eventList.add(eventEntity);
        eventEntity = new EventEntity();
        eventEntity.setId(132);
        eventEntity.setNombre("Nombre ficticio 2");
        eventEntity.setFecha("13/12/2015");
        eventList.add(eventEntity);

    }

    @Override
    protected int getResourceLayout() {
        return R.layout.recycler_view;
    }
}
