import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class MachineCodeWriter 
{
    public static void writeToBinaryFile(String fileName, List<Instruction> parsedAssemblyInstructions) throws FileNotFoundException
    {
        //Initialize variables
        PrintWriter fileWriter = new PrintWriter(fileName);


        //Looping though List writing to file.
        for(Instruction inst : parsedAssemblyInstructions)
        {
            fileWriter.println(inst.getMachineCode()); //Writes the line to file
        }
        
        fileWriter.close(); //Won't save the line to the file if it isn's closed after each line
    }
}
