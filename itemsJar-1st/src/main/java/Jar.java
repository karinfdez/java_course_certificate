import java.util.Random;

public class Jar{
  private String mItemName;
  private int mMaxNumber;
  private static final int MINIMUM=1;


  public Jar(String itemName,int maxNumber){
      mItemName=itemName;
      mMaxNumber=maxNumber;
  }
    
  public String getItemName(){
    return mItemName;
  }
    
  public int getMaxNumber(){
    return mMaxNumber;
  }
    
  public int getMinimum(){
    return MINIMUM;
  }
    
  public int createRandomNumber(){
    Random random = new Random();
    int rightAmountItems=MINIMUM + random.nextInt(mMaxNumber - MINIMUM + 1);
    
    return rightAmountItems;
  }

}