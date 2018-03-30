package com.example.ghislain.project_api.ui.modele;

/**
 * Created by GHISLAIN on 30/03/2018.
 */

public class ItemAPI {
    private String nomApi, fournisseurAPI;
    private int logoAPI;

    public ItemAPI(String nomApi, String fournisseurAPI, int logoAPI) {
        this.nomApi = nomApi;
        this.fournisseurAPI = fournisseurAPI;
        this.logoAPI = logoAPI;
    }

    public String getNomApi() {
        return nomApi;
    }

    public void setNomApi(String nomApi) {
        this.nomApi = nomApi;
    }

    public String getFournisseurAPI() {
        return fournisseurAPI;
    }

    public void setFournisseurAPI(String fournisseurAPI) {
        this.fournisseurAPI = fournisseurAPI;
    }

    public int getLogoAPI() {
        return logoAPI;
    }

    public void setLogoAPI(int logoAPI) {
        this.logoAPI = logoAPI;
    }

    @Override
    public String toString() {
        return "ItemAPI{" +
                "nomApi='" + nomApi + '\'' +
                ", fournisseurAPI='" + fournisseurAPI + '\'' +
                ", logoAPI=" + logoAPI +
                '}';
    }
}
