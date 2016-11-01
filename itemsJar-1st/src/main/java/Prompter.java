import java.util.Scanner;

public class Prompter{
  
  private String nameItem;
  private String maxItems;
  private int counter=0;


  public boolean invalidInput( String nameItem){
    return nameItem.equals(null) || nameItem.isEmpty();   
  }

  public int getCounter(){
    return counter;
  }

  public String promptUser(String question){
      String userAnswer;
      do{
         System.out.println(question);
         Scanner scanner = new Scanner(System.in);
         userAnswer = scanner.nextLine();
         if (invalidInput(userAnswer)){
          System.out.printf("Invalid input. Try again.%n");
         }
      }while(invalidInput(userAnswer));
  
      return userAnswer;
  }

  public void showMessage(String message){
    System.out.printf(message);
  }
  
  public String promptItemNameJar(){
      showMessage("%n ADMINISTRATOR SETUP %n ===================%n");
      nameItem=promptUser("Name of items in the jar: ");
  
      return nameItem;
  }

  public int promptMaxNumber(){
    maxItems=promptUser("Maximum number of " + nameItem + " in the jar:");
    showMessage(" %n Player %n ========%n Your goal is to guess how many "+ nameItem + " are in the jar. Your guess should be between 1 and " + maxItems +". %n");
    hitEnter();
    return Integer.parseInt(maxItems);
  }

public int guessMaxNumber(int randomAmounItems){
  
    int userAnswer;
    
   do{
      System.out.printf("Guess:");
      Scanner scanner = new Scanner(System.in);
      userAnswer = scanner.nextInt();
      counter++;
      if (userAnswer < randomAmounItems){
        System.out.println("Your guess is too low!.Try again.");
      }else if(userAnswer > randomAmounItems){
        System.out.println("Your guess is too high!.");
      }
      if(userAnswer > Integer.parseInt(maxItems)){
        System.out.println("Your guess must be less than " + maxItems+". Try again.");
      }
   
   }while(userAnswer!=randomAmounItems);

    return userAnswer;
  }

  //Program pause until user hits Enter
  public static void hitEnter(){
    System.out.println("Ready? (Press enter to start guessing)");
    Scanner keyboard = new Scanner(System.in);
    keyboard.nextLine();
  }

}
