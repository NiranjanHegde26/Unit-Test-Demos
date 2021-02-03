import java.util.HashMap;
import java.util.Scanner;

class Validator{
    public boolean LuhnAlgorithm(String number){

    int digits = number.length();
 
    int sum = 0;
    boolean twodig = false;
    for (int i = digits - 1; i >= 0; i--) 
    {
 
        int d = number.charAt(i) - '0';
 
        if (twodig == true)
            d = d * 2;
 
        
        sum += d / 10;
        sum += d % 10;
 
        twodig = !twodig;
    }
    return (sum % 10 == 0);
    }
}
class CreditCard{

    private String CardBrand;
    private String CardNo;
    private String cvv;
    private boolean cardvalidity = false;
    private boolean cvvvalidity = false;
    
    
    public void setCardNo(String number){   
        HashMap<String,String> cardName = new HashMap<>();

        cardName.put("6011","Discover");
        cardName.put("5123","MasterCard");
        cardName.put("3411","American Express");
        cardName.put("4011","Visa");

        HashMap<String,Integer> cardLength= new HashMap<>();
        cardLength.put("American Express",15);
        cardLength.put("Discover",12);
        cardLength.put("Visa",12);
        cardLength.put("MasterCard",12);

        Validator valid = new Validator();
        if(valid.LuhnAlgorithm(number)){
            if(cardName.containsKey(number.substring(0,4)))
            {
                CardBrand = cardName.get(number.substring(0,4));
                
                if(cardLength.get(CardBrand) == number.length())
                {
                    CardNo = number;
                    cardvalidity = true;
                }
                else
                    System.out.println("Invalid Length");

            }
        else
            System.out.println("Unkown Card");

        }
        else  System.out.println("Invalid Card No.");


        
    }

    public void setCVV(String cvv){
        HashMap<String,Integer> cardCVV = new HashMap<>();
        cardCVV.put("Discover",3);
        cardCVV.put("MasterCard",3);
        cardCVV.put("American Express",4);
        cardCVV.put("Visa",3);
       
        if(cardCVV.containsValue(cvv.length()))
            {
                this.cvv = cvv;
                cvvvalidity = true;
            }
        else
            System.out.println("Invalid CVV");
    }

    public String getCardBrand(){
        return CardBrand;
    }

    public boolean getCardValidity(){
            return cardvalidity;
    }
    public boolean getCVVValidity(){
        return cvvvalidity;
}

}

public class CreditCardValidator{
    public static void main(String[] args) {
        CreditCard cc = new CreditCard();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Card Number");
        cc.setCardNo(sc.nextLine());

        System.out.println("Enter Card CVV");
        cc.setCVV(sc.nextLine());

        if(cc.getCVVValidity() && cc.getCardValidity())
            System.out.println("The Card and CVV are valid and the card is : " + cc.getCardBrand());
        else
            System.out.println("Incorrect Details! Try Again!");

           
    }
}