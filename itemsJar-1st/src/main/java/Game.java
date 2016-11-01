public class Game {
    public static void main(String[] args) {

      //Prompt user for name and max number of items on a jar
        Prompter game=new Prompter();
        String nameItemJar= game.promptItemNameJar();
      int maxNumberJar=game.promptMaxNumber();
    
     //Creates new jar with name and maxNumber from user
     //From random number created, user has to guess the right amount.
        Jar theJar= new Jar(nameItemJar,maxNumberJar);
        int amountItemsJar=theJar.createRandomNumber();
        int userCorrectAnswer=game.guessMaxNumber(amountItemsJar);
        System.out.printf("%n Congratulations-You guessed that there are %d %s in the jar!.It took %d guess(es) to get it right. %n",userCorrectAnswer,nameItemJar,game.getCounter());
    }
}
