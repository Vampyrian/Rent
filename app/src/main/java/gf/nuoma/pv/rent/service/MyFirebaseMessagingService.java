package gf.nuoma.pv.rent.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    static final String LOG_TAG = "Mano Servisas";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(LOG_TAG, "Gavau zinute nuo: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size()>0) {
            Log.d(LOG_TAG, "Gavau zinute su duomenimis: " + remoteMessage.getData());
        }

        if(remoteMessage.getNotification() != null) {
            Log.d(LOG_TAG, "Zinutes notifikacijos kunas: " + remoteMessage.getNotification().getBody());
        }

    }
}
