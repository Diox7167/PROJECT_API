package bdd_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSQLQuery {

    DBConnect con;

    public TSQLQuery() {
        con = new DBConnect();
    }

    // retourne toutes les bases de données de la SGBD
    public List<String> getDataBases() {
        List<String> lst = new ArrayList<>();

        try {

            con.setRs(con.getSt().executeQuery("SHOW databases"));

            while (con.getRs().next()) {
                lst.add(con.getRs().getString("database"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }
    //retourne toutes les tables de la base de données sélectionner
    public List<String> getTablesBase() {
        List<String> lst = new ArrayList<>();

        try {

            con.setRs(con.getSt().executeQuery("SHOW TABLES FROM " + Outils_Const.TABLENAME));

            while (con.getRs().next()) {
                lst.add(con.getRs().getString("Tables_in_test"));

            }
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(lst.get(i));
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

            con.setRs(con.getSt().executeQuery("describe " + tableName));

            while (con.getRs().next()) {
                lst.add(con.getRs().getString("FIELD"));
            }
            
            for(String colonneName : lst) {
                System.out.print(colonneName + ", ");
            }
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void TSQLQueryUpdate(String req) {
        int state = 0;
        try {
            state = con.getSt().executeUpdate(req);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void TSQLQueryExecute(String tableName, String... params) {
        String chaine = "";
        List<String> listColonne = new ArrayList<>(); // liste des colonnes passées en parametre
        List<String> lst = new ArrayList<>(); // liste de noms des colonnes de table en cours
        listColonne.addAll(Arrays.asList(params));
        try {

            // recuperation des noms des colonnes de la table en cours
            con.setRs(con.getSt().executeQuery("describe " + tableName));
            while (con.getRs().next()) {
                lst.add(con.getRs().getString("FIELD"));
            }

            // Selection de toutes les colonnes de la table en cours
            if (listColonne.isEmpty()) {
                con.setRs(con.getSt().executeQuery("select * from " + tableName + " order by id"));

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
                con.setRs(con.getSt().executeQuery("select " + chaine + " from " + tableName + " order by id"));

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
    public void TSQLQueryUpdate(String req)
    {
        int state = 0;
        try {
            state = con.getSt().executeUpdate(req);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public void TSQLQueryExecute(String baseName)
    {
        List<String> lst = new ArrayList<String>();
        try {
          
            // recuperation des noms des colonnes de la table en cours
            con.setRs(con.getSt().executeQuery("describe "+baseName));
            while(con.getRs().next())
            {
                lst.add(con.getRs().getString("FIELD"));
               // System.out.println(con.getRs().getString("FIELD"));
            }
            
            // Projection de toutes les colonnes de la table en cours
             con.setRs(con.getSt().executeQuery("select * from "+baseName));
             while (con.getRs().next()) 
             {
               
                for(int i=0 ; i<lst.size(); i++)
                {
                    System.out.print(con.getRs().getString(lst.get(i))+" ");
                }
                 System.out.println("");
                 
                
             }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     */
    
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
