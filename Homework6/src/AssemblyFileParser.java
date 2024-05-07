import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssemblyFileParser 
{
    //Member variables
    private List<String> cleanAssemblyCode;
    private Scanner fileReader;


    //Constructor
    public AssemblyFileParser(String fileName) throws FileNotFoundException
    {
        //Initialize variables
        File assemblyFile = new File(fileName);
        cleanAssemblyCode = new ArrayList<>();

        //Throw exception if file does not exist
        if(!assemblyFile.exists())
        {
            throw new FileNotFoundException("File Not Found.");
        }

         //Throw exception if file is empty
        if(assemblyFile.length() == 0)
        {
            throw new FileNotFoundException("File Is Empty");
        }


        //Create a Scanner to read the file and make first pass
        //First Pass Objective: remove all comments and whitespace from the file
        fileReader = new Scanner(assemblyFile);
        makeFirstPass();


        //Second Pass Objective:
        //makeSecondPass();
        fileReader.close();



    }


    private void makeFirstPass() 
    {
        //iniitalize variables
        String rawLine, cleanLine, returnLine;

        //Loop through file
        while(fileReader.hasNextLine())
        {
            //For Each line, clean the line of comments and spaces.
            rawLine = fileReader.nextLine();
            cleanLine = clean(rawLine);

            //If the entire line is not a comment or whitespace, add it to our assemblyCode
            if(!cleanLine.isEmpty())
            {
                cleanAssemblyCode.add(cleanLine);
            }
        }
    }


    //------ Clean Method ------\\

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
