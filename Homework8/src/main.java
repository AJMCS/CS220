package Homework8.src;

import java.util.Scanner;

public class main 
{
    public static void main(String[] args) 
    {
        String fileName;
        Scanner keyboard = new Scanner(System.in);
        

        System.out.println("Enter name of file to translate withoug extension");
        fileName = keyboard.nextLine();

        try 
        {
            Parser parser = new Parser(fileName);

            while(parser.hasMoreCommands())
            {
                parser.advance();

                System.out.println(parser.getCommand() + ", " + parser.getSegment()+ ", " + parser.getIndex());
            }

            parser.close();

        } catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
        

    }
}
