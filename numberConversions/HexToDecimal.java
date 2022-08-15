package numberConversions;

public class HexToDecimal {
    
    public static int hexadecimalToDecimal(String str){  
        //declare hexadecimal string
        String hex = "0123456789ABCDEF";  
        str = str.toUpperCase(); 
        //initialize counter 
        int num = 0;  
        //loop through the input string
        //get character at specific index
        //return index and multiply by base 16 and add index to convert 
        for (int i = 0; i < str.length(); i++)  
        {  
            char ch = str.charAt(i);  
            int n = hex.indexOf(ch);  
            num = 16*num + n;  
        }  
        return num;  
       }  
}
