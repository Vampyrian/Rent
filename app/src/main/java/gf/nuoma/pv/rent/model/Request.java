package gf.nuoma.pv.rent.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Request {

    public String date;
    public int count;
    public int price;
    public int accept;
    public String owner;
    public String room;
    @Exclude
    public String key;

    public Request () {

    }

    public Request (String date, int count, int price, int accept) {
        this.date = date;
        this.count = count;
        this.price = price;
        this.accept = accept;
        owner = "nobody";
        room = "free";
        key = "noKey";
    }
}
