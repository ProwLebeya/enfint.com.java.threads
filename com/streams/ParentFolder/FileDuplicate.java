package com.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;

public class FileDuplicate {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the source file to copy from");
        String sourceName = scan.nextLine();

        File sourceFile = new File(sourceName);

        System.out.println("Enter destination file to copy to");

        String destinationName = scan.nextLine();

        File destFile = new File(destinationName);

        try{
            copyContent(sourceFile, destFile);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void copyContent(File a, File b) throws Exception
    {
        FileInputStream input = new FileInputStream(a);
        FileOutputStream output = new FileOutputStream(b);

        try{
            int byteNumber;

            while((byteNumber = input.read()) != -1){//read bytes of data (-1 is the end of file)
                output.write(byteNumber);//write byte of data
            }
        }finally{
            if(input != null && output != null){
                input.close();//close input stream
                output.close();//close output stream
            }
        }
        System.out.println("File copied");
    }

}
