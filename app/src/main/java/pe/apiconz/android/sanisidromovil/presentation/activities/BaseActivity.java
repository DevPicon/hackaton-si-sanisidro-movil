package pe.apiconz.android.sanisidromovil.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pe.wanavana.android.sanisidromovil.R;

/**
 * Created by Armando on 12/12/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected Toolbar toolbar;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        //TODO Cambiar por ButterKnife
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        onCreateView();
    }

    protected abstract void onCreateView();

    protected abstract int getLayoutResource();
}
