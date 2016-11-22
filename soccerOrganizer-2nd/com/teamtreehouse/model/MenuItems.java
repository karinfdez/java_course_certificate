package com.teamtreehouse.model;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

  
public class MenuItems{
 
  private String mOption1="Create new team";
  private String mOption2="Add players";
  private String  mOption3="Remove players from team";
  Map<Integer,String> mMenuItemsList;
  private BufferedReader mReader;
  Player[] mPlayers = Players.load();
  Map <Integer,Team> mTeamList;
  Map <Integer,String> mListPlayer;
  public static final int MAX_PLAYERS = 12;
  
  
  public MenuItems(){
     mMenuItemsList = new HashMap<>();
     mMenuItemsList.put(1,mOption1);
     mMenuItemsList.put(2,mOption2);
     mMenuItemsList.put(3,mOption3);
     mReader=new BufferedReader(new InputStreamReader(System.in));
     mTeamList=new HashMap<>();
     mListPlayer=new HashMap<>();
  }
  
  public String promptToAction() throws IOException{
    
    System.out.println("Choose a number to execute an action or type 'quit' to stop:" ) ;
    
    for(int item: mMenuItemsList.keySet()){
        System.out.println(item +"."+mMenuItemsList.get(item));
     }
    String userChoice=mReader.readLine();
    
    return userChoice.trim();
    
  }
                                                    
                                                    
  public void userChoice(){
    
    String userChoice="";
    do{
        try{
          
          userChoice= promptToAction();
          switchUserInput(userChoice);
          
        }catch(IOException ioe){
          System.out.println("Incorrect input");
          ioe.printStackTrace();
        }
    }while(!userChoice.toLowerCase().equals("quit"));
    
  }
  
  
   public String promptNewTeam(String option) throws IOException{
      String userInput="";
       do{
        
         System.out.println("Please enter the "+ option);
         userInput=mReader.readLine();
       
        }while(userInput==null || userInput.isEmpty());
        
       return userInput;
    }
  
  
  public void createNewTeam() throws IOException{
     
      String team="";
      String coach="";
      int counter=1;
    //Check that no more teams are created than there are players
    if(mTeamList.size()< mPlayers.length){
      try{
            team=promptNewTeam("team name");
            coach=promptNewTeam("coach");
            
          }catch(IOException ioe){
            System.out.println("Incorrect input");
            ioe.printStackTrace();
          }
         mTeamList.put(counter,new Team(team,coach));
         counter++;
    }else{
        System.out.printf("You exceed the limit for creating teams.There are %d teams and %d players already %n",mTeamList.size(),mPlayers.length);
       
      }
  }
  
  public void showListOfteams(){
    for(Map.Entry<Integer, Team> entry : mTeamList.entrySet()){ 
          System.out.printf("%d. %s %n", entry.getKey(),entry.getValue().getTeamName()); 
    }

  }
  public void addPlayers() throws IOException{
    String userChoice="";
    int numberTeam=0;
    
    if(mTeamList.size()>0){
      do{
         System.out.println("Select number related to the team in order to add a player: ");
       
//         for(int item: mTeamList.keySet()){
//           System.out.println(item +"."+mTeamList.get(item).getTeamName());
//         }
        showListOfteams();
        numberTeam=catchUserInput(numberTeam);
        
        }while(!mTeamList.containsKey(numberTeam));
        
         addPlayersToTeam();

    }else{
         System.out.printf("You have to create at least 1 team first to add players to it.%n%n"); 
    }
      
  }
  
  
  public int catchUserInput(int numberTeam) throws IOException{
    
     try {
            numberTeam = Integer.parseInt(mReader.readLine());
          } catch(NumberFormatException e) {
               System.out.println("This is not a number");
               System.out.println(e.getMessage());
          }
     return numberTeam;
  }
             
   
  public void addPlayersToTeam() throws IOException{
    String fullName="";
    int numberTeam=0;
    int counter=1;
    boolean isListFull=false;
      
    for(int i=0;i< mPlayers.length;i++){
        fullName=mPlayers[i].getFirstName() +" "+ mPlayers[i].getLastName();
        mListPlayer.put(counter,fullName);
    }
    do{
     System.out.println("Please, add players to team typing the number associated with the player. Maximum 11 players/team");
   
      for(int key: mListPlayer.keySet()){
        System.out.println(key + "." + mListPlayer.get(key));
      }
      numberTeam=catchUserInput(numberTeam);
      Team team= mTeamList.get(numberTeam);
      isListFull=team.getPlayers().size()<MAX_PLAYERS;
       if(isListFull){
        team.addPlayer(mListPlayer.get(numberTeam));
       }else{
        System.out.printf("There are already %d players on the team",team.getPlayers().size());
       }
       System.out.println("Players of "+team.getTeamName());
       Set<String> teamList=team.getPlayers();
       for(String player: teamList){
        System.out.println(player);
       }
     }while(!mListPlayer.containsKey(numberTeam));
     
  }
  
  public void removePlayersFromTeam() throws IOException{
    int numberTeam=0;
    do{
         showListOfteams();
         System.out.println("Select the number associated to the team from which you would like to remove player(s)");
         numberTeam=catchUserInput(numberTeam);
         Team team= mTeamList.get(numberTeam);
         if(team.getPlayers().size()<=0){
          System.out.println("There are not players added to this team");
         }
        }while(!mTeamList.containsKey(numberTeam));
        System.out.printf("you are inside of %s",mTeamList.get(numberTeam));
   
  }
  
  
   public void switchUserInput(String userChoice){
     Team myTeam=new Team("name","otherName");
      
     switch (userChoice){
        case "1":  try{
                      createNewTeam();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
        case "2":  try{
                     addPlayers();
                   }catch(IOException e){
                     e.printStackTrace();
                   }
                   break;
        case "3":  try{
                    removePlayersFromTeam();
                   }catch(IOException e){
                    e.printStackTrace();
                   }
                    break;
        case "quit": System.out.println("Thanks for playing");
                     break;
        default: System.out.println("Incorrect answer");
      }  
   }
}



