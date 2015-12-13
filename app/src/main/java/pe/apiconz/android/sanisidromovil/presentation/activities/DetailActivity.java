package pe.apiconz.android.sanisidromovil.presentation.activities;

import android.support.v7.app.ActionBar;

import pe.apiconz.android.sanisidromovil.R;

/**
 * Created by Armando on 12/13/2015.
 */
public class DetailActivity extends BaseActivity {
    @Override
    protected void onCreateView() {
        setSupportActionBar();
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
