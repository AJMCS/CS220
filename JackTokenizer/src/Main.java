import java.io.IOException;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        //Initialize
        String fileName;
        Scanner keyboard = new Scanner(System.in);

        //Prompt user to enter file name
        System.out.println("Please enter file name.");
        fileName = keyboard.nextLine();

        //Create tokenizer object
        JackTokenizer jt;
        try 
        {
            jt = new JackTokenizer(fileName);

            //Parse all tokens
            jt.parseTokens();

            //Close
            jt.close();

        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
        }
    }

        
}
