package pe.apiconz.android.sanisidromovil.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import pe.apiconz.android.sanisidromovil.R;
import pe.apiconz.android.sanisidromovil.application.SanIsidroMovilApp;
import pe.apiconz.android.sanisidromovil.model.entities.EventDetailEntity;

/**
 * Created by Armando on 12/13/2015.
 */
public class DetailActivity extends BaseActivity {

    private static final String TAG = DetailActivity.class.getCanonicalName();

    @Bind(R.id.detail_event_title)
    protected TextView detailEventName;
    @Bind(R.id.detail_event_date)
    protected TextView detailEventDate;
    @Bind(R.id.detail_event_time)
    protected TextView detailEventTime;
    @Bind(R.id.detail_event_artist)
    protected TextView detailEventArtist;
    @Bind(R.id.detail_event_place)
    protected TextView detailEventPlace;

    private EventDetailEntity evento;


    @Override
    protected void onCreateView() {
        setSupportActionBar();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            long itemId = extras.getLong("itemId", 1);
            evento = new EventDetailEntity();

            getEventDetail(itemId);
            detailEventName.setText(evento.getEvento());
            detailEventArtist.setText(evento.getArtista());
            detailEventDate.setText(evento.getFecha());
            detailEventTime.setText(evento.getHora());
            detailEventPlace.setText(evento.getLugar());
            this.notify();

        }
    }

    private void getEventDetail(long itemId) {

        SanIsidroMovilApp application = (SanIsidroMovilApp) getApplicationContext();
        Firebase firebase = application.getFirebaseReferenceForActivities();
        Query query = firebase.orderByChild("ID").startAt(itemId).endAt(itemId);
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "count" + dataSnapshot.getChildrenCount());
                if (dataSnapshot.getChildren().iterator().hasNext()) {
                    DataSnapshot postSnapshot = dataSnapshot.getChildren().iterator().next();
                    evento = new EventDetailEntity();
                    evento.setEvento(postSnapshot.child("EVENTO").getValue().toString());
                    evento.setFecha(postSnapshot.child("FECHA").getValue().toString());
                    evento.setHora(postSnapshot.child("HORA").getValue().toString());
                    evento.setLugar(postSnapshot.child("LUGAR").getValue().toString());
                    evento.setEspacio(postSnapshot.child("ESPACIO").getValue().toString());
                    evento.setArtista(postSnapshot.child("ARTISTA").getValue().toString());
                    evento.setId(postSnapshot.child("ID").getValue(int.class));
                    Log.d(TAG,evento.toString());

                } else {
                    Log.d(TAG, "No se pudo obtener registro " + itemId);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    protected void setSupportActionBar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }
}
