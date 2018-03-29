package com.example.ghislain.project_api.ui;

/**
 * Created by GHISLAIN on 29/03/2018.
 */
public class ListeAPI {
    private int color;
    //private String logoAPI;
    private String nomAPI;
    private String fournisseurAPI;

    public ListeAPI(int color, String nomAPI, String fournisseurAPI) {
        this.color = color;
        //this.logoAPI = logoAPI;
        this.nomAPI = nomAPI;
        this.fournisseurAPI = fournisseurAPI;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    /*
    public String getLogo() {
        return logoAPI;
    }

    public void setLogo(String logoAPI) {
        this.logoAPI = logoAPI;
    }
    */

    public String getPseudo() {
        return nomAPI;
    }

    public void setPseudo(String nomAPI) {
        this.nomAPI = nomAPI;
    }

    public String getText() {
        return fournisseurAPI;
    }

    public void setText(String fournisseurAPI) {
        this.fournisseurAPI = fournisseurAPI;
    }
}