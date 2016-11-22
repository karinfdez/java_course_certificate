package com.teamtreehouse.model;

import java.util.Set;
import java.util.TreeSet;

public class Team{
  
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
  
  
}