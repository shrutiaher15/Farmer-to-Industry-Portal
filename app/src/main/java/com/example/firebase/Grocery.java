package com.example.firebase;
public class Grocery {

    public String productName;
    public String productPrice;
    public String productdate;
    public String productQty;
    public String Id;
    public  String distance;
    public String profit;


    public Grocery(String productName, String productPrice, String productdate, String productQty,String Id,String distance,String profit) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productdate = productdate;
        this.productQty = productQty;
        this.Id=Id;
       this.profit=profit;
        this.distance=distance;
    }

    public String getProductQty() {
        return productQty;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public String getProductWeight() {
        return productdate;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductId(){return Id;}
    public  String getDistance(){return distance;}
    public String getProfit(){return profit;}
}
