package gf.nuoma.pv.rent.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RoomModel {

    public String name;

    @Exclude
    public String key;

    public RoomModel() {}

    public RoomModel(String name) {
        this.name = name;
        key = "NoKey";
    }

}
