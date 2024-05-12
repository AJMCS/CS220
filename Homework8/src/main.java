package Homework8.src;

import java.util.Scanner;

public class main 
{
    public static void main(String[] args) 
    {
        //Initialize variables
        String fileName;
        Scanner keyboard = new Scanner(System.in);
        
        //User input file name
        System.out.println("Enter name of file to translate withoug extension");
        fileName = keyboard.nextLine();

        try 
        {
            Parser parser = new Parser(fileName);
            CodeWriter writer = new CodeWriter(fileName);

            while(parser.hasMoreCommands())
            {
                //read vm code
                parser.advance();

                //Build asm code
                writer.writeCode(parser.getCommand(), parser.getSegment(), parser.getIndex());
            }

            parser.close();
            writer.close();
            System.out.println("Success.");

        } catch (Exception e) 
        {
            //Throw error if file is not found
            System.err.println(e.getMessage());
        }
    }
}
