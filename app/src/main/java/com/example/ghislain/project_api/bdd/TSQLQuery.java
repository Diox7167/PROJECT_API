package com.example.ghislain.project_api.bdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GHISLAIN on 17/03/2018.
 */

public class TSQLQuery {
    private DBConnect con;

    public TSQLQuery() {
        con = new DBConnect();
    }

    public DBConnect getCon() {
        return con;
    }

    public void setCon(DBConnect con) {
        this.con = con;
    }

    /**
     *
     * @param req
     * @return
     */

    public boolean TSQLQueryDelete(String req)
    {
        int state = 0;
        try {
            state = con.getSt().executeUpdate("delete from "+Outils_Const.TABLENAME+" "+req);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return state != 0;
    }

    public boolean TSQLQueryUpdate(String req) {
        int state = 0;
        try {
            state = con.getSt().executeUpdate(req);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return state != 0;
    }

    public boolean TSQLQueryExecute(String req)
    {
        Boolean state = false;
        try {

            con.setRs(con.getSt().executeQuery(req));
            while(con.getRs().next())
            {
                state = true;
                System.out.println("true");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(!state)
            System.out.println("false");
        return state;
    }

    public void TSQLQuerySelect(String... params) {
        String chaine = "";
        List<String> listColonne = new ArrayList<>(); // liste des colonnes passées en parametre
        List<String> lst = new ArrayList<>(); // liste de noms des colonnes de table en cours
        listColonne.addAll(Arrays.asList(params));
        try {

            // recuperation des noms des colonnes de la table en cours
            con.setRs(con.getSt().executeQuery("describe " + Outils_Const.TABLENAME));
            while (con.getRs().next()) {
                lst.add(con.getRs().getString("FIELD"));
            }

            // Projection de toutes les colonnes de la table en cours
            if (listColonne.isEmpty()) {
                con.setRs(con.getSt().executeQuery("select * from " + Outils_Const.TABLENAME + " order by id"));

                while (con.getRs().next()) {
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.print(con.getRs().getString(lst.get(i)) + " ");

                    }
                    System.out.println("");
                }
            }//Selection des colonnes passées en parametre de la table en cours
            else {
                int j = 0;
                for (j = 0; j < listColonne.size(); j++) {
                    chaine += listColonne.get(j) + ",";
                }

                // on supprime la derniere virgule de trop
                chaine = chaine.substring(0, chaine.length() - 1);
                con.setRs(con.getSt().executeQuery("select " + chaine + " from " + Outils_Const.TABLENAME + " order by id"));

                while (con.getRs().next()) {
                    for (int i = 0; i < listColonne.size(); i++) {
                        System.out.print(con.getRs().getString(listColonne.get(i)) + " ");

                    }
                    System.out.println("");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*

    public void TSQLQueryUpdate(String baseName, String... datas)
    {
        int state = 0;

        List<String> cle = new ArrayList<String>();
        List<String> valeur = new ArrayList<String>();

        String req = "";
        String chaineCle = "";
        String chaineValeur = "";



        int n = datas.length;
        for(int i = 0; i<datas.length; i++)
        {
            if(i < n/2)
            {
                cle.add(datas[i]);
                chaineCle +=datas[i]+",";
            }
            else
            {
                valeur.add(datas[i]);
                chaineValeur +=""+datas[i]+",";
            }

        }

        chaineCle    = chaineCle.substring(0,chaineCle.length()-1);
        chaineValeur = chaineValeur.substring(0, chaineValeur.length()-1);

        try {
            state = con.getSt().executeUpdate("insert into "+baseName+"("+chaineCle+") values ("+chaineValeur+")");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
      /*
    public static void menu(TSQLQuery query)
    {
       List<String> databases = query.getDataBases();
       List<String> tablesName = query.getTablesBase();

        int choice = 0;
        Scanner sc = new Scanner(System.in);


        System.out.println("Choisissez la base de données : ");
        query.getDataBases();

        for(int i=0; i<databases.size(); i++)
            System.out.println((i+1)+"_"+databases.get(i));
        System.out.println("\n");

        choice = sc.nextInt();
        Outils_Const.DATABASE = databases.get(choice-1);

        System.out.println("\n");
        System.out.println("------Base "+Outils_Const.DATABASE+"------");

        query.con.loadConnectMYSQL();


         System.out.println("\n");
         System.out.println("Selectionner la une table");

         choice = sc.nextInt();
         Outils_Const.TABLENAME = tablesName.get(choice-1);

         System.out.println("\n");
        System.out.println("------Table "+Outils_Const.TABLENAME+"------");
        query.getTablesBase();


    }
*/
}
