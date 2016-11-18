package com.teamtreehouse.model;
  
public class Team{
  
  private String mTeamName;
  private String mCoach;
  
  
  public Team(String teamName,String coach){
    mTeamName=teamName;
    mCoach=coach;
  }
  
  public void setTeamName(String name){
    mTeamName=name;
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
  
  
}