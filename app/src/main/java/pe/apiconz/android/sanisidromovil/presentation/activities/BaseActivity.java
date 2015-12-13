package pe.apiconz.android.sanisidromovil.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import pe.apiconz.android.sanisidromovil.R;

/**
 * Created by Armando on 12/12/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        ButterKnife.bind(this);
        
        onCreateView();
    }

    protected abstract void onCreateView();

    protected abstract int getLayoutResource();
}
