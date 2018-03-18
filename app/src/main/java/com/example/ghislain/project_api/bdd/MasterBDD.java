package com.example.ghislain.project_api.bdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHISLAIN on 17/03/2018.
 */

public class MasterBDD {

    private TSQLQuery query;

    public MasterBDD()
    {
        query = new TSQLQuery();
    }

    public TSQLQuery getQuery() {
        return query;
    }

    public void setQuery(TSQLQuery query) {
        this.query = query;
    }

    //////////////////////////
    //    User Option      //
    ////////////////////////

    //Login
    public boolean userLogIn(String pseudo, String password)
    {
        boolean state = false;
        String req = "select * from users where nom ='"+pseudo+"' and prenom = '"+password+"'";
        return query.TSQLQueryExecute(req);
    }

    //LogOut
    public boolean userLogOut()
    {
        return false;
    }

    //Register
    public boolean userRegister(String nom, String prenom)
    {
        String req = "insert into users(nom,prenom) values('"+nom+"','"+prenom+"')";
        return query.TSQLQueryUpdate(req);
    }

    // Supprimer son compte
    public boolean userDelete()
    {
        return false;
    }

    //Modifier les informations du compte
    public boolean userUpdate()
    {
        return false;
    }

    // Recuperer la liste des API
    public boolean userListeAPI()
    {
        return false;
    }

    //Authers functions

    public List<String> getTablesBase() {
        List<String> lst = new ArrayList<>();

        try {

            query.getCon().setRs(query.getCon().getSt().executeQuery("SHOW TABLES FROM " + Outils_Const.BASENAME));

            while (query.getCon().getRs().next()) {
                lst.add(query.getCon().getRs().getString("Tables_in_test"));

            }
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(lst.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }

    // retourne toutes les bases de données de la SGBD
    public List<String> getDataBases() {
        List<String> lst = new ArrayList<>();

        try {

            query.getCon().setRs(query.getCon().getSt().executeQuery("SHOW databases"));

            while (query.getCon().getRs().next()) {
                lst.add(query.getCon().getRs().getString("database"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }

    //Lister le nom de toutes le colonnes de la table courante
    public void displayColonnes(String tableName) {
        List<String> lst = new ArrayList<>();

        System.out.println("------DATABASE : "+Outils_Const.BASENAME+"------");
        System.out.println("Table : "+tableName);

        try {

            query.getCon().setRs(query.getCon().getSt().executeQuery("describe " + tableName));

            while (query.getCon().getRs().next()) {
                lst.add(query.getCon().getRs().getString("FIELD"));
            }

            for(String colonneName : lst) {
                System.out.print(colonneName + ", ");
            }
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
