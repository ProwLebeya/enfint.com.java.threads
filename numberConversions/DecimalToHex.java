package numberConversions;

public class DecimalToHex {
    
    public static void decimalToHexadecimal(int a){
        // Digits in hexadecimal number system
         char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        
         //string to hold hex value
         String str = "";
         //counter for hex array
         int remainder;
         
         //iteration and printing hex from array
         while(a>0){
            remainder = a%16;
            str = hex[remainder] + str;
            a = a/16;
        }
        System.out.println("Conversion 1: Decimal to Hexadecimal: "+str);
    }

}
