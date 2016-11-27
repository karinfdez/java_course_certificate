package com.teamtreehouse.model;

import java.util.Set;
import java.util.TreeSet;

public class Team implements Comparable{
  
  private String mTeamName;
  private String mCoach;
  private Set <String> mListPlayers;
  
  
  public Team(String teamName,String coach){
    mTeamName=teamName;
    mCoach=coach;
    mListPlayers= new TreeSet<>();
  }
  
  public void setTeamName(String name){
    mTeamName=name;
  }
  
  public void addPlayer(String player){
    mListPlayers.add(player);
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
  
  public Set<String> getPlayers(){
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