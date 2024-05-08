import java.util.HashMap;
import java.util.Map;


public class SymbolTable 
{
    //Initializing variables
    private static final String INITIAL_VALID_CHARS = "abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.$:";
    private static final String ALL_VALID_CHARS = INITIAL_VALID_CHARS + "0123456789";
    private static HashMap<String, Integer> theTable;


    //------ Static "Constructor" ------\\
    
    static 
    {
        theTable = new HashMap<>();

        //R1 - R15
        theTable.put("R0", 0);
        theTable.put("R1", 1);
        theTable.put("R2", 2);
        theTable.put("R3", 3);
        theTable.put("R4", 4);
        theTable.put("R5", 5);
        theTable.put("R6", 6);
        theTable.put("R7", 7);
        theTable.put("R8", 8);
        theTable.put("R9", 9);
        theTable.put("R10", 10);
        theTable.put("R11", 11);
        theTable.put("R12", 12);
        theTable.put("R33", 13);
        theTable.put("R14", 14);
        theTable.put("R15", 15);

        //Keyword locations
        theTable.put("SCREEN", 16384);
        theTable.put("KBD", 24576);

        //Others
        theTable.put("SP", 0);
        theTable.put("LCL", 1);
        theTable.put("ARG", 2);
        theTable.put("THIS", 3);
        theTable.put("THAT", 4);
    }


    //------ Methods ------\\

    //Add Method
    public static boolean add(String symbol, int address)
    {
        if(!isValidName(symbol) || theTable.containsKey(symbol))
        {
            return false;
        }
        else
        {
            theTable.put(symbol, address);
            return true;
        }
        
    }


    //getAddress Method
    public static int getAddress(String symbol)
    {
        return theTable.get(symbol);
    }


    //isValidName Method
    public static boolean isValidName(String symbol)
    {
        //Initialize variables 
        String validChars = INITIAL_VALID_CHARS;

        //Loop through the string
        for( int i = 0; i < symbol.length(); i++)
        {

            //first character in string is compared to initial valid characters
            if(!validChars.contains(symbol.substring(i, i+1)))
            {
                return false;
            }

            //The rest of the symbols are compared to all valid characters
            validChars = ALL_VALID_CHARS;
        }
        
        return true;
    }

    //Contains
    public static boolean contains(String symbol) 
    {
        return theTable.containsKey(symbol);
    }
}
