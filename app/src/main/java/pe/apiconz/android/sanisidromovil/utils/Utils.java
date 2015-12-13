package pe.apiconz.android.sanisidromovil.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.apiconz.android.sanisidromovil.model.entities.EventEntity;

/**
 * Created by Armando on 12/13/2015.
 */
public class Utils {
    public static String formatDateTime(EventEntity eventEntity) {

        if (eventEntity != null) {
            if (eventEntity.getHora() != null && !(eventEntity.getHora().isEmpty())) {
                return String.format("%s - %s", eventEntity.getFecha(), eventEntity.getHora());
            } else {
                return eventEntity.getFecha();
            }
        }
        return "";
    }

    public static long getTimeMillisecond(String oldTime){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy - HH:mm");
        formatter.setLenient(false);
        Date oldDate = null;
        try {
            oldDate = formatter.parse(oldTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long oldMillis = oldDate.getTime();
        return oldMillis;
    }


    public static String getCurrentDate(int when) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yy");//dd/MM/yyyy
        Date now;
        if(when == 1){
             now = new Date();
        }else{
           now = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
        }
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
