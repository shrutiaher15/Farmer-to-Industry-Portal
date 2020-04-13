package com.example.firebase;
/*** class for near by farmer display**/
public class farmer_detail {
    String farmer_name;
    String farmer_phoneno;
    String dis;
    String id;

    public String getFarmer_name() {
        return farmer_name;
    }
  //  public String getFarmer_id(){return farmer_id;}


    public String getDis() {
        return dis;
    }


    public void setDis(String dis){ this.dis=dis; }
    public  void setId (String id){this.id=id;}
    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public String getFarmer_phoneno() {
        return farmer_phoneno;
    }
    public  String getId(){return id;}
    //public String getDis(){return dis;}

    public void setFarmer_phoneno(String farmer_phoneno) {
        this.farmer_phoneno = farmer_phoneno;
    }

    public farmer_detail(String farmer_name, String farmer_phoneno,String id,String dis) {
        this.farmer_name = farmer_name;
        this.farmer_phoneno = farmer_phoneno;
        this.id=id;
        this.dis=dis;

    }
}
