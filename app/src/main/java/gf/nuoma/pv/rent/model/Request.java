package gf.nuoma.pv.rent.model;


public class Request {

    public String date;
    public String count;
    public String price;
    public int accept;

    public Request () {

    }

    public Request (String date, String count, String price, int accept) {
        this.date = date;
        this.count = count;
        this.price = price;
        this.accept = accept;
    }
}
