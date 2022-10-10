package com.streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class DependencyCheck {
    public static void main(String[] args) {
        try {
            File folder = new File(args[0]);//folder to iterates
            FileWriter output = new FileWriter(args[1]);//output file
            
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    getFolderFiles(file, output);
                } else {
                    copyToFile(file.getPath(), output, file.getName());
                }
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
	 * Copy contents of current file to new file
	 * 
	 * @param currentDir
	 * @param writer
     * @param parentFile
	 */
    private static void copyToFile(String current, FileWriter writer, String parentFile) {
        try {
            String currentLine;
            File currentFile = new File(current);
            FileReader reader = new FileReader(currentFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            LineNumberReader lineReader = new LineNumberReader(bufferedReader);
            int count = 0;
            while ((currentLine = lineReader.readLine()) != null) {
                if (currentLine.startsWith("*require") && currentLine.endsWith("*") && count == 0) {
                    System.out.println(currentLine.substring(10, currentLine.length() - 2));
                    System.out.println("Extracting contents of child class");
                    if (currentLine.substring(10, currentLine.length() - 2).contains(parentFile)) {
                        throw new IOException("Error: cyclic dependency exist between files");
                    }
                    copyToFile(currentLine.substring(10, currentLine.length() - 2), writer, currentFile.getName());
                    count++;
                } else if (currentLine.startsWith("*require") && currentLine.endsWith("*") && count != 0) {
                    continue;//skip other dependencies
                } else {
                    writer.write(currentLine);
                    writer.write(System.getProperty("line.separator"));
                }
           }
       } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }


     /**
	 * Get files from current folder
	 * 
	 * @param file
	 * @param wiriter
	 */
    private static void getFolderFiles(File file, FileWriter writer) {
        for (File subFolder : file.listFiles()) {
            if (subFolder.isDirectory()) {
            getFolderFiles(subFolder, writer);
            } else {
            copyToFile(subFolder.getPath(), writer, subFolder.getName());
            }
        }
    }
}