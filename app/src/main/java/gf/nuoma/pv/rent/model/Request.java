package gf.nuoma.pv.rent.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Request {

    public int date;
    public int count;
    public int price;
    public int accept;
    public String owner;
    @Exclude
    public String key;

    public Request () {

    }

    public Request (int date, int count, int price, int accept) {
        this.date = date;
        this.count = count;
        this.price = price;
        this.accept = accept;
        owner = "nobody";
        key = "noKey";
    }
}
