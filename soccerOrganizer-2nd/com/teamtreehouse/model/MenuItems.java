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
  private List mTeamList=new ArrayList();
  
  
  public MenuItems(){
     mMenuItemsList = new HashMap<>();
     mMenuItemsList.put(1,mOption1);
     mMenuItemsList.put(2,mOption2);
     mMenuItemsList.put(3,mOption3);
     mReader=new BufferedReader(new InputStreamReader(System.in));  
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
    //Check that no more teams are created than there are players
    if(mTeamList.size()< mPlayers.length){
      try{
            team=promptNewTeam("team name");
            coach=promptNewTeam("coach");
            
          }catch(IOException ioe){
            System.out.println("Incorrect input");
            ioe.printStackTrace();
          }
         mTeamList.add(new Team(team,coach));
    }else{
        System.out.printf("You exceed the limit for creating teams.There are %d teams and %d players already %n",mTeamList.size(),mPlayers.length);
       
      }
  }
  
  public void addPlayers(){
    int counter=1;
    if(mTeamList.size()>0){
      System.out.println("Select team to add player: ");
      for(Team team : (ArrayList<Team>) mTeamList){
        System.out.printf("%d. %s %n",counter,team.getTeamName());
        counter++;
      }
    }else{
      System.out.printf("You have to create at least 1 team first to add players to it.%n%n");
    }
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
        case "2":  addPlayers();
                   break;
        case "3":  System.out.println("Option3");
                   break;
        case "quit": System.out.println("Thanks for playing");
                     break;
        default: System.out.println("Incorrect answer");
      }  
   }
}



