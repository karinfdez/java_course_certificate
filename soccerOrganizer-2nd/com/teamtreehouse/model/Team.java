package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Team implements Comparable{
  
  private String mTeamName;
  private String mCoach;
  private Map <Integer, Player> mListPlayers;
  private int experiencePlayers;
  private int inexperiencePlayers;
  List <Integer> listExperiencePlayers;
  
  
  public Team(String teamName,String coach){
    mTeamName=teamName;
    mCoach=coach;
    mListPlayers= new TreeMap<>();
    this.experiencePlayers=0;
    this.inexperiencePlayers=0;
    this.listExperiencePlayers=new ArrayList<>();
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
  
  public int getExperiencePlayers(){
    return experiencePlayers;
  }
  
  public int getInexperiencePlayers(){
    return inexperiencePlayers;
  }
  
  public void addOneToExperienceCounter(){
    experiencePlayers++;
  }
  
   public void addOneToInexperienceCounter(){
    inexperiencePlayers++;
  }
  
  public void lessOneToExperienceCounter(){
    experiencePlayers--;
  }
  
   public void lessOneToInexperienceCounter(){
    inexperiencePlayers--;
  }
  
  public void addCountersToList(){
    listExperiencePlayers.add(0,experiencePlayers);
    listExperiencePlayers.add(1,inexperiencePlayers);
  }
  
  public List<Integer> getListOfcounter(){
    return listExperiencePlayers;
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