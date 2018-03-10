
package bdd_api;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect
{
    private Connection con;// objet qui gere la connexion
    private Statement st; // objet qui gere les requetes 
    private ResultSet rs; // Resultat de la requete
    
    
    public DBConnect()
    {
        loadDriverMYSQL();
        connectMYSQL();
    }
    
    //Methode qui permet de charger le driver de MYSQL
    private void loadDriverMYSQL()
    {  
        try {
            Class.forName(Outils_Const.DRIVERNAME);
        } catch (Exception e) {
            System.out.println(e.getMessage()+"\nImpossible de charger le Driver de MYSQL");
        }
    }
    
    //Connexion à la base de donnée
    private void connectMYSQL()
    {
        try {
            con = DriverManager.getConnection(Outils_Const.URL,Outils_Const.USER,Outils_Const.PASSWORD);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void loadConnectMYSQL()
    {
        try {
               con = DriverManager.getConnection(Outils_Const.URL,Outils_Const.USER,Outils_Const.PASSWORD);
               st = con.createStatement();
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }
    //accesseur

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    
}
