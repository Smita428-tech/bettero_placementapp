package com.example.bettero;

public class dataclass {
    private String datatitle;
    private String datadesc;
    private String dataimage;
    private String key;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }





    //private String dataimage;

    public String getDatatitle() {
        return datatitle;
    }


    public String getDatadesc() {
        return datadesc;
    }


    public String getDataimage() {
      return dataimage;
   }

    public dataclass(String datatitle, String datadesc, String dataimage) {
        this.datatitle = datatitle;
        this.datadesc = datadesc;
       // this.dataLang = dataLang;
        this.dataimage = dataimage;
    }
    public dataclass(){

    }


}
