package Homework8.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser
{
    private final Scanner fileScanner;
    private String command;
    private String segment;
    private int index;
    
    

    //------ Getters ------\\
    
    public String getCommand() {
        return command;
    }

    public String getSegment() {
        return segment;
    }

    public int getIndex() {
        return index;
    }



    //------ File Management Methods------\\

    public boolean hasMoreCommands()
    {
        return fileScanner.hasNextLine();
    }


    public Parser(String fileName) throws FileNotFoundException

    {
        //Open new file
        fileScanner = new Scanner(new File(fileName + ".vm"));
    }


    public void advance()
    {
        resetCommand();

        //Initialize variables
        String cleanLine = getCleanLine(fileScanner.nextLine());
        String[] parts = cleanLine.split(" ");

        command = parts[0];

        if(parts.length == 3)
        {
            segment = parts[1];
            index = Integer.parseInt(parts[2]);
        }

    }

    public void close()
    {
        fileScanner.close();
    }



    //------ Helper Methods ------\\

    private void resetCommand() 
    {
       //Reset all variables 
        command = "";
        segment = "";
        index = -1;
    }

    private String getCleanLine(String rawLine) 
    { 
        //Initializing variables
         String cleanLine = rawLine.replaceAll("\\s+", " ").trim();    
         int commentIndex = cleanLine.indexOf("//");
         
 
         //Remove comments
         if (commentIndex != -1)
         {
             cleanLine = cleanLine.substring(0, commentIndex).trim();
         }
 
         return cleanLine;
    }
}
