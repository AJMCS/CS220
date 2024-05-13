import java.io.*;
import java.util.*;

public class JackTokenizer 
{
    //Declare objects
    private PushbackReader fileReader;
    private PrintWriter fileWriter;

    //Initialize Lists
    private static final List<String> keywordsList = Arrays.asList
    (
        "class", "constructor", "function", "method", "field", "return",
        "static", "var", "int", "char", "boolean", "void", "true",
        "false", "null", "this", "let", "do", "if", "else", "while"
    );

    private static final List<Character> symbolList = Arrays.asList
    (
         '{', '}', '(', ')', '[', ']', '.', ',', ';', '+',
          '-', '*', '/', '&', '|', '<', '>', '=', '~'
    );



    //------ Constructor ------\\

    public JackTokenizer(String fileName) throws FileNotFoundException 
    {
        fileReader = new PushbackReader(new FileReader(fileName + ".jack"));
        fileWriter = new PrintWriter(fileName + "T.xml");

        //Print root element
        fileWriter.println("<tokens>");
    }


    
    //------ Methods ------\\

    public void parseTokens() throws IOException 
    {
        int c;
        while((c = fileReader.read()) != -1)
        {
            //Determine Character
            if(c == '/')
            {
                //Determine if its a in-line or block comment
                c = fileReader.read();

                if(!readComment(c))
                {
                    printToken("symbol", "/");
                }
            }
            else if (c == '"') //Check for a String constant
            {
                String stringConst = readStringConst();
                printToken("stringConstant", stringConst);
            }
            else if(symbolList.contains((char) c)) //Check for symbols
            {
                printToken("symbol", xmlSymbol(c));
            }
            else if(Character.isLetter(c) || c == '_') //Check for keywords or identifiers
            {
                String token = readToken(c);
                if(keywordsList.contains(token))
                {
                    printToken("keyword", token);
                }
                else
                {
                    printToken("identifier", token);
                }
            }
            else if(Character.isDigit(c))
            {
                String intConst = readIntConst(c);
                printToken("integerConstant", intConst);
            }
        }
    }


    private String readIntConst(int c) throws IOException 
    {
        //Create string
        StringBuilder intConst = new StringBuilder();

        do
        {
            intConst.append((char) c); //Append to string

        }//While file has not ended and character is a digit
        while((c = fileReader.read()) != -1 && Character.isDigit(c));

        //Unread and return
        fileReader.unread(c);
        return intConst.toString();
    }


    public void close() throws IOException 
    {
        fileWriter.println("</tokens>");
        fileReader.close();
        fileWriter.close();
    } 



    //------ Helper Methods ------\\

    private String xmlSymbol(int c) 
    {
        switch(c)
        {
            case '<':
            return "&lt;";

            case '>':
            return "&gt;";

            case '&':
            return "&amp;";

            case '"':
            return "&quot;";

            default:
            return String.valueOf((char) c);
        }
    }

    private void printToken(String tag, String value) 
    {
        fileWriter.println("\t<" + tag + "> " + value + " </" + tag + ">");
    }

    private String readToken(int c) throws IOException 
    {
        //Create string
        StringBuilder token = new StringBuilder();
        
        do
        {
            token.append((char) c); //Append to final string

        }//Loop as long as file has not ended and c is a digit, letter, or underscore
        while((c = fileReader.read()) != -1 && (Character.isLetterOrDigit(c) || c == '_'));
        
        //Unread and return
        fileReader.unread(c);
        return token.toString();
    }

    private String readStringConst() throws IOException 
    {
        //Declare variables/objects
        int c;
        StringBuilder stringConst = new StringBuilder();

        //Append to string while inside quotes
        while((c = fileReader.read()) != -1 && c != '"')
        {
            stringConst.append((char) c);
        }

        //return string
        return stringConst.toString();
    }

    private boolean readComment(int c) throws IOException 
    {
        switch(c)
        {
            //Single Line Comment
            case '/':

            while((c = fileReader.read()) != -1 && c != '\n'); //read until end of line or end of file
            break;
            
            //Block Comment
            case '*':

            while((c = fileReader.read()) != -1)
            {
                if(c == '*')
                {
                    if((c = fileReader.read()) == '/')
                    {
                        return true;
                    }
                    else
                    {
                        fileReader.unread(c);
                    }
                }
            }
            break;
            
            //Not a Comment
            default:
            fileReader.unread(c);
            return false;
        }
        //Was a comment
        return true;
    }
}
