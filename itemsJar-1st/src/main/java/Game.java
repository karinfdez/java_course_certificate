public class Game {
    public static void main(String[] args) {

    //Prompt Administrator for name and max number of items on a jar
        Prompter game=new Prompter();
        String nameItemJar= game.promptItemNameJar();
        int maxNumberJar=game.promptMaxNumber();
    
     //Creates new jar with name and maxNumber from previous answer
     //From random number created, user has to guess the right amount.
        Jar theJar= new Jar(nameItemJar,maxNumberJar);
        int amountItemsJar=theJar.createRandomNumber();
        int userCorrectAnswer=game.guessMaxNumber(amountItemsJar);
        game.showResults(userCorrectAnswer,nameItemJar);
    }
}
