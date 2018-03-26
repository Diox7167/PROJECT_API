package com.example.ghislain.project_api.api;

import java.net.URLEncoder;

public class MasterAPI {

	private WOW wow;
	private LOL lol;
	
    private String lastContent;
    private int lastCode;
    
    public MasterAPI() {
    	this.wow = new WOW();
    	this.lol = new LOL();
    }
    
    public String getLastResponseContent() {
    	return this.lastContent;
    }
    
    public int getResponseCode() {
    	return this.lastCode;
    }

    
    
	/*************************************************
	 **                                             **
	 ** 			  MAGIC CARD MARKET             **
	 **                                             **
	 *************************************************/

    
    
    
	/*************************************************
	 **                                             **
	 ** 			  WORLD OF WARCRAFT             **
	 **                                             **
	 *************************************************/
	public boolean WOWRequestGuild(String realm, String guild) {
		String url = "https://eu.api.battle.net/wow/guild/" + realm + "/" + guild + "?fields=achievements%2Cchallenge&locale=fr_FR&apikey=dkh87sv2w2kvksy2mpmeh9perc3f7ayk";
		url = url.replace(" ", "%20");
		if(this.wow.request(url)) {
			this.lastContent = this.wow.getResponseContent();
			this.lastCode = this.wow.getResponseCode();
			return true;
		}
		return false;
	}
	public boolean WOWRequestBossList() {
		if(this.wow.request("https://eu.api.battle.net/wow/boss/?locale=fr_FR&apikey=dkh87sv2w2kvksy2mpmeh9perc3f7ayk")) {
			this.lastContent = this.wow.getResponseContent();
			this.lastCode = this.wow.getResponseCode();
			return true;
		}
		return false;
	}
    
    
    
	/*************************************************
	 **                                             **
	 ** 			  LEAGUE OF LEGENDS             **
	 **                                             **
	 *************************************************/
    public boolean LOLRequestSummoner(String summonerName) {
    	summonerName = summonerName.replace(" ", "%20");
    	if(this.lol.request("https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summonerName + "?api_key=RGAPI-e543de3e-4334-4665-b17a-1799b893dda4")) {
    		this.lastContent = this.lol.getResponseContent();
			this.lastCode = this.lol.getResponseCode();
			return true;
    	}
    	return false;
    }
    public boolean LOLRequestRecentMatchs(String accountId) {
    	if(this.lol.request("https://euw1.api.riotgames.com/lol/match/v3/matchlists/by-account/" + accountId + "/recent?api_key=RGAPI-e543de3e-4334-4665-b17a-1799b893dda4")) {
    		this.lastContent = this.lol.getResponseContent();
			this.lastCode = this.lol.getResponseCode();
			return true;
    	}
    	return false;
    }
}


