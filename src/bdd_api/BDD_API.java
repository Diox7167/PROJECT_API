package bdd_api;
import API.MasterAPI;
import java.util.*;
public class BDD_API 
{
    public static void main(String[] args)
    {
       MasterAPI mapi = new MasterAPI();
		
		/*************************************************
		 **                                             **
		 ** 			  WORLD OF WARCRAFT     **
		 **                                             **
		 *************************************************/
		if (mapi.WOWRequestGuild("Ysondre", "New World")) {
			System.out.println("WOW Guild: " + mapi.getResponseContent() + "\n");
		}else{
                    System.out.println("Erreur: " + mapi.getResponseCode());
                }
		//if (mapi.WOWRequestBossList()) {
		//	System.out.println("WOW Boss: " + mapi.getResponseContent() + "\n");
		//}
		

		
		/*************************************************
		 **                                             **
		 ** 			  MAGIC CARD MARKET             **
		 **                                             **
		 *************************************************/
		if(mapi.MCMRequestAccount("Yishutia")) {
			System.out.println("MCM Account: " + mapi.getResponseContent() + "\n");
		}
		if(mapi.MCMRequestMessages()) {
			System.out.println("MCM Messages: " + mapi.getResponseContent() + "\n");
		}
		
		
		
		/*************************************************
		 **                                             **
		 ** 			  LEAGUE OF LEGENDS             **
		 **                                             **
		 *************************************************/
                if(mapi.LOLRequestSummoner("Aguerix")) {
			System.out.println("LOL Summoner: " + mapi.getResponseContent() + "\n");
		}
		if(mapi.LOLRequestRecentMatchs("231376361")) {
			System.out.println("LOL Recent matchs: " + mapi.getResponseContent() + "\n");
		} 
                
                MasterBDD mbdd = new MasterBDD();
                
                
                for(int i=0;i<mbdd.getQuery().getDataBases().size(); i++)
                    System.out.println(mbdd.getQuery().getDataBases().get(i));
                    
           
    }

    
}
