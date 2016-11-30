package com.teamtreehouse.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Team implements Comparable{
  
  private String mTeamName;
  private String mCoach;
  private Map <Integer, Player> mListPlayers;
  
  
  public Team(String teamName,String coach){
    mTeamName=teamName;
    mCoach=coach;
    mListPlayers= new TreeMap<>();
  }
  
  public void setTeamName(String name){
    mTeamName=name;
  }
  
  public void addPlayer(Player player){
    mListPlayers.put(mListPlayers.size()+1,player);
  }
  
  public void setCoach(String coach){
    mCoach=coach;
  }
  
  public String getTeamName(){
    return mTeamName;
  }
  
  public String getCoach(){
    return mCoach;
  }
  
  public Map <Integer, Player> getPlayers(){
    return mListPlayers;
  }
  
  @Override
  public int compareTo(Object obj) {
		 Team other=(Team) obj;
     if(equals(other)){
      return 0;
     }
     return mTeamName.compareTo(other.mTeamName);

	}
  
}