package com.teamtreehouse.model;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

  
public class MenuItems{
 
  private String mOption1="Create new team";
  private String mOption2="Add players";
  private String  mOption3="Remove players from team";
  private String  mOption4="Show List of Players";
  private String  mOption5="League Balance Report";
  private String  mOption6="Show team heigh's relation";
  Map<Integer,String> mMenuItemsList;
  private BufferedReader mReader;
  private Set<Team> mTeam;
  Player[] mPlayers = Players.load();
  Map <Integer,Team> mTeamList;
  Map <Integer,Player> mListPlayer;
  List <Integer>listExperiencePlayers;
  Map<String,List<Integer>> listExperienceByTeams;
  List <Integer> listCounters;
  Set<Integer>mListByHeigh;
  public static final int MAX_PLAYERS = 11;
  
  
  public MenuItems(){
     mMenuItemsList = new HashMap<>();
     mMenuItemsList.put(1,mOption1);
     mMenuItemsList.put(2,mOption2);
     mMenuItemsList.put(3,mOption3);
     mMenuItemsList.put(4,mOption4);
     mMenuItemsList.put(5,mOption5);
     mMenuItemsList.put(6,mOption6);
     mReader=new BufferedReader(new InputStreamReader(System.in));
     mTeamList=new HashMap<>();
     mListPlayer=new HashMap<>();
     mTeam=new TreeSet<>();
     listExperiencePlayers=new ArrayList<>();
     listExperienceByTeams=new HashMap<>();
     listCounters= new ArrayList<>();
     mListByHeigh=new TreeSet<>();
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
    //Compare by Last Name and then by first name
     Arrays.sort(mPlayers);
     for(Player player: mPlayers){
         mListPlayer.put(mListPlayer.size()+1,player);
      }
    
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
         mTeam.add(new Team(team,coach));
         addOrderListToTeamList();
         
         
    }else{
        System.out.printf("You exceed the limit for creating teams.There are %d teams and %d players already %n",mTeamList.size(),mPlayers.length);
      }
    
  }
  
  public void addOrderListToTeamList(){
    mTeamList.clear();
    for(Team theTeam: mTeam){
      if(!mTeamList.containsValue(theTeam.getTeamName())){
        mTeamList.put(mTeamList.size()+1,theTeam);
      }
    }
  }
  
  public void showListOfteams(){
    for(int number: mTeamList.keySet()){
      System.out.printf("%d. %s %n",number,mTeamList.get(number).getTeamName());
    }
  }
 
  
  
  public void addPlayers() throws IOException{
    String userChoice="";
    int numberTeam=0;
    addOrderListToTeamList();
    if(mTeamList.size()>0){
      do{
        System.out.println("Select number related to the team in order to add a player: ");
        showListOfteams();
        numberTeam=catchUserInput();
        
        }while(!mTeamList.containsKey(numberTeam));
        addPlayersToTeam(numberTeam);
    }else{
         System.out.printf("You have to create at least 1 team first to add players to it.%n%n"); 
    }
      
  }
  
  
  public int catchUserInput() throws IOException{
    
    int numberTeam=0;
     try {
            numberTeam = Integer.parseInt(mReader.readLine());
          } catch(NumberFormatException e) {
               System.out.println("This is not a number");
               System.out.println(e.getMessage());
          }
     return numberTeam;
  }
             
   
  public void addPlayersToTeam(int numberTeam) throws IOException{
    
    String fullName="";
    int numberPlayer=0;
    boolean isListOk=false;
    Set<String> playerList;
    Team team=mTeamList.get(numberTeam);
      
    do{
      System.out.println("Please, add players to team typing the number associated with the player. Maximum 11 players/team");
      System.out.println("list size: " + mListPlayer.size());
        for(int number: mListPlayer.keySet()){
        System.out.printf(" %d. %s, %s | height(Inches): %d |previous Experience?: %b %n",number,   mListPlayer.get(number).getLastName(),mListPlayer.get(number).getFirstName(),mListPlayer.get(number).getHeightInInches(),mListPlayer.get(number).isPreviousExperience());
        
        }
      numberPlayer=catchUserInput();
     }while(!mListPlayer.containsKey(numberPlayer));
    
    //Verify that max of players on team are 11
    
      isListOk=team.getPlayers().size()<MAX_PLAYERS;
       if(isListOk){
        Player player=mListPlayer.get(numberPlayer);
        team.addPlayer(player);
        System.out.printf("Added %d player(s) to %s %n",team.getPlayers().size(),team.getTeamName());
         
        if(player.isPreviousExperience()){
               team.addOneToExperienceCounter();
          }else{
               team.addOneToInexperienceCounter();  
          }
         
       }else{
        System.out.printf("There are already %d players on the team %n",team.getPlayers().size());
       }
      
  }
  
  
  public void removePlayersFromTeam() throws IOException{
    int numberTeam=0;
    Team team= mTeamList.get(numberTeam);
    
    if(mTeamList.size()>0){
        do{
         System.out.println("Select the number associated to the team from which you would like to remove player(s)");
          showListOfteams();
         numberTeam= catchUserInput();
       }while(!mTeamList.containsKey(numberTeam));
      
        Team theTeam=mTeamList.get(numberTeam);
        
        if(theTeam.getPlayers().size()>0){
          System.out.println("Select a number to remove players from "+theTeam.getTeamName());
         for(int number: theTeam.getPlayers().keySet()){
          System.out.printf("%d. %s,%s %n",number,theTeam.getPlayers().get(number).getLastName(),theTeam.getPlayers().get(number).getFirstName());
         }
          numberTeam=catchUserInput();
          if(theTeam.getPlayers().containsKey(numberTeam)){
            theTeam.getPlayers().remove(numberTeam);
            System.out.printf("%n %d players left on %s %n",theTeam.getPlayers().size(),theTeam.getTeamName());
          }
        }else{
         System.out.printf("You have to add players to this team.%n%n"); 
        }
      

    }else{
         System.out.printf("You have to create at least 1 team first to add players to it.%n%n"); 
    }
   
  }
  
  public void printPlayers() throws IOException{
    int numberTeam=0;
     addOrderListToTeamList();
    if(mTeamList.size()>0){
       do{
         System.out.println("Select the number associated to the team from which you would like to print the list of player(s)");
          showListOfteams();
          numberTeam= catchUserInput();
          
        }while(!mTeamList.containsKey(numberTeam));
         Team theTeam= mTeamList.get(numberTeam);
         if(theTeam.getPlayers().size()>0){
           System.out.printf("%s player's list: %n",theTeam.getTeamName());
           for(int number: theTeam.getPlayers().keySet()){
            System.out.printf("%d. %s,%s %n",number,theTeam.getPlayers().get(number).getLastName(),theTeam.getPlayers().get(number).getFirstName());
           }
         }else{
           System.out.println("There are no players for "+ theTeam.getTeamName()+ " team. Add players first.");
         }
    }else{
      System.out.println("You have to create teams first and add players.");
    }
    
  }
  
  //Prints report of player's experience
  
  public void leagueBalanceReport() throws IOException{
    
    int numberTeam=0;
     
    if(mTeamList.size()>0){
        do{
           System.out.println("Select the number associated to the team from which you would like to print the list of player(s)");
            showListOfteams();
            numberTeam= catchUserInput();
            
          }while(!mTeamList.containsKey(numberTeam));
           Team theTeam= mTeamList.get(numberTeam);
           
          if (theTeam.getPlayers().size()>0){
              theTeam.addCountersToList();
              System.out.println("League Balance Report:");
        
            for(Team team: mTeamList.values()){
                System.out.println(team.getTeamName());
                System.out.println("Counters");
                for(int counter:team.getListOfcounter()){
                  System.out.println(counter);
                 }
              System.out.printf(team.getTeamName()+" team: ");
              System.out.printf("Total Experience players: %d, ",team.getListOfcounter().get(0));
              System.out.printf("Total Inexperience players: %d %n%n",team.getListOfcounter().get(1));
              
             }
           }else{
            System.out.printf("%n You must add players to %s team", theTeam.getTeamName());
           }
      }else{
        System.out.println("You must add team(s) first");
      }
  }
  
 
   public void showTeamByHeight() throws IOException{
      int numberTeam=0;
      if(mTeamList.size()>0){
        do{
           System.out.println("Select the number associated to the team in order to show list of player's heigh");
            showListOfteams();
            numberTeam= catchUserInput();
            
         }while(!mTeamList.containsKey(numberTeam));
           Team theTeam= mTeamList.get(numberTeam);
         if(theTeam.getPlayers().size()>0){
           for(Player player : theTeam.getPlayers().values()){
              mListByHeigh.add(player.getHeightInInches());
           }
           System.out.printf("%n Heigh's relation in %s team: %n",theTeam.getTeamName());
           for(int height: mListByHeigh){
              System.out.println(height + " Inches");
           }
           
         }else{
          System.out.println("You must add players to "+ theTeam.getTeamName()+" first.");
         }
           
       }else{
        System.out.println("You must add team(s) first");
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
        case "4":  try{
                    printPlayers();
                   }catch(IOException e){
                    e.printStackTrace();
                   }
                    break;
       case "5":  try{
                    leagueBalanceReport();
                   }catch(IOException e){
                    e.printStackTrace();
                   }
                    break;
       case "6":  try{
                    showTeamByHeight();
                   }catch(IOException e){
                    e.printStackTrace();
                   }
        case "quit": System.out.println("Thanks for playing");
                     break;
        default: System.out.println("Incorrect answer");
      }  
  }
}
