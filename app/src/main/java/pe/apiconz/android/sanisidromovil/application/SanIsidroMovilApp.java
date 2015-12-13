package pe.apiconz.android.sanisidromovil.application;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Armando on 12/12/2015.
 */
public class SanIsidroMovilApp extends Application {

    private String FIREBASE_URL = "https://demohacksi.firebaseio.com/";
    private String FIREBASE_CHILD_ACTIVITIES = "results";

    private Firebase firebase;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        firebase = new Firebase(FIREBASE_URL);
    }

    public Firebase getFirebaseReferenceForActivities() {
        return firebase.child(FIREBASE_CHILD_ACTIVITIES);
    }

}
