import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssemblyFileParser 
{
    //Member variables
    private List<String> cleanAssemblyCode;
    private List<Instruction> parsedAssemblyInstructions;
    private Scanner fileReader;

    


    //Constructor
    public AssemblyFileParser(String fileName) throws FileNotFoundException
    {
        //Initialize variables
        File assemblyFile = new File(fileName);
        cleanAssemblyCode = new ArrayList<>();
        parsedAssemblyInstructions = new ArrayList<>();

        //Throw exception if file does not exist
        if(!assemblyFile.exists())
        {
            throw new FileNotFoundException("File Not Found." + assemblyFile.getAbsolutePath());
        }

         //Throw exception if file is empty
        if(assemblyFile.length() == 0)
        {
            throw new FileNotFoundException("File Is Empty");
        }


        //Create a Scanner to read the file and make first pass
        //First Pass Objective: remove all comments and whitespace from the file and resolves all labels
        fileReader = new Scanner(assemblyFile);
        makeFirstPass();


        //Second Pass Objective: Resolves all variables and Creates the A and C Instructions
        makeSecondPass();

        //Close scanner object
        fileReader.close();
    }

    //------ Getter ------\\
    public List<Instruction> getParsedAssemblyInstructions() 
    {
        return parsedAssemblyInstructions;
    }


    //------ Make First Pass ------\\

    private void makeFirstPass() 
    {
        //Iniitalize variables
        String rawLine, cleanLine, returnLine;
        int romAddress = 0;

        //Loop through file
        while(fileReader.hasNextLine())
        {
            //For Each line, clean the line of comments and spaces.
            rawLine = fileReader.nextLine();
            cleanLine = clean(rawLine);

            //If the entire line is not a comment or whitespace, add it to our assemblyCode
            if(!cleanLine.isEmpty())
            {

                //Check if the cleanLine is a label
                if(cleanLine.startsWith("(") && cleanLine.endsWith(")"))
                {
                    insertLabelInSymbolTable(cleanLine, romAddress);
                }
                else //if cleanLine is not empty and not a label
                {
                    cleanAssemblyCode.add(cleanLine);
                    romAddress++;
                }

                
            }
        }
    }


    //------ Make Second Pass ------\\

    private void makeSecondPass() 
    {
        //initialize Variables
        int variableAddress = 16;
        int address;
        String symbol;

        for(String code: cleanAssemblyCode)
        {
            //Determine if it's an A-Instruction
            if(code.startsWith("@"))
            {
                try
                {
                    address = Integer.parseInt(code.substring(1));
                }
                catch(NumberFormatException e) //Address was not a number (i.e. @R8)
                {
                    //Get the symbol instead
                    symbol = code.substring(1);

                    //If the symbol table already has the symbol inside it. Get its corresponding value.
                    if(SymbolTable.contains(symbol))
                    {
                        address = SymbolTable.getAddress(symbol);
                    }
                    else
                    {
                        SymbolTable.add(symbol, variableAddress);
                        address = variableAddress++;
                    }
                } 

                parsedAssemblyInstructions.add(new AInstruction("@" + address));
            }
            else //Otherwise code must be a C-Instruction
            {
                parsedAssemblyInstructions.add(new CInstruction(code));
            }
        }
    }


    //------ Clean Method ------\\


    private void insertLabelInSymbolTable(String cleanLine, int romAddress) 
    {
        //Extract label from parenthesis wrapping around it
        String label = cleanLine.substring(1, cleanLine.length() -1);

        //Add the label to the symbolTable at the correct rom address
        SymbolTable.add(label, romAddress);
    } 


    public String clean(String rawLine)
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


    //-------- toString ------\\

    @Override
    public String toString()
    {
        //Initialize variable
        StringBuilder returnString = new StringBuilder();

        //Loop through the array and append to the final output string
        for(String cleanLine : cleanAssemblyCode)
        {
            returnString.append(cleanLine).append("\n");
        }

        return returnString.toString();
    }
    
}
