import java.util.HashMap;

public class CInstruction extends Instruction 
{
    //Initialize variables
    private String dest;
    private String comp;
    private String jump;
    private static HashMap<String, String> destCodes = new HashMap<>(8);
    private static HashMap<String, String> jumpCodes = new HashMap<>(8);
    private static HashMap<String, String> compCodes = new HashMap<>(8);


    //------ Static "Constructor" ------\\

    static
    {
        //Dest
        destCodes.put(null, "000");
        destCodes.put("M", "001");
        destCodes.put("D", "010");
        destCodes.put("MD", "011");
        destCodes.put("A", "100");
        destCodes.put("AM", "101");
        destCodes.put("AD", "110");
        destCodes.put("AMD", "111");

        //Jump
        jumpCodes.put(null, "000");
        jumpCodes.put("JGT", "001");
        jumpCodes.put("JEQ", "010");
        jumpCodes.put("JGE", "011");
        jumpCodes.put("JLT", "100");
        jumpCodes.put("JNE", "101");
        jumpCodes.put("JLE", "110");
        jumpCodes.put("JMP", "111");

        //Comp
        compCodes.put("0", "0101010");
        compCodes.put("1", "0111111");
        compCodes.put("-1", "0111010");
        compCodes.put("D", "0001100");
        compCodes.put("A", "0110000");
        compCodes.put("!D", "0001101");
        compCodes.put("!A", "0110001");
        compCodes.put("-D", "0001111");
        compCodes.put("-A", "0110011");
        compCodes.put("D+1", "0011111");
        compCodes.put("1+D", "0011111");
        compCodes.put("A+1", "0110111");
        compCodes.put("1+A", "0110111");
        compCodes.put("D-1", "0001110");
        compCodes.put("A-1", "0110010");
        compCodes.put("D+A", "0000010");
        compCodes.put("A+D", "0000010");
        compCodes.put("D-A", "0010011");
        compCodes.put("D&A", "0000000");
        compCodes.put("A&D", "0000000");
        compCodes.put("D|A", "0010101");
        compCodes.put("A|D", "0010101");
        
        compCodes.put("M", "1110000");
        compCodes.put("!M", "1110001");
        compCodes.put("-M", "1110011");
        compCodes.put("M+1", "1110111");
        compCodes.put("1+M", "1110111");
        compCodes.put("M-1", "1110010");
        compCodes.put("D+M", "1000010");
        compCodes.put("M+D", "1000010");
        compCodes.put("D-M", "1010011");
        compCodes.put("M-D", "1000111");
        compCodes.put("D&M", "1000000");
        compCodes.put("M&D", "1000000");
        compCodes.put("D|M", "1010101");
        compCodes.put("M|D", "1010101");

    }



    public CInstruction (String code)
    {
        this.assemblyCode = code;

        paseCodeIntoPart();
    }



    private void paseCodeIntoPart() 
    {
        //Initialize variables
        int indexOfEquals = assemblyCode.indexOf("=");
        int indexOfColon = assemblyCode.indexOf(";");
        int endIndex = assemblyCode.length();
        int beginIndex = 0;
        

        //------Capture Dest ------\\

        if(indexOfEquals != -1)
        {
            dest = assemblyCode.substring(0, indexOfEquals).trim();
        }

        //------ Capture Jump ------\\

        if(indexOfColon != -1)
        {
            jump = assemblyCode.substring(indexOfColon + 1).trim();
        }


        //------ Capture Comp ------\\

        //If there exists an equals sign, beginIndex should be the index after it
        if(indexOfEquals != -1)
        {
            beginIndex = indexOfEquals + 1;
        }

        //If there exists a colon, endIndex should be the index before it
        if (indexOfColon != -1) 
        {
            endIndex = indexOfColon;
        }

        comp =  assemblyCode.substring(beginIndex, endIndex).trim();


        //Append machine code together
        machineCode = "111"  + compCodes.get(comp) + destCodes.get(dest) + jumpCodes.get(jump);
    }


    //------ toString ------\\

    @Override
    public String toString()
    {
        return "C-Instruction [" +
        "Dest = " + dest +
        " | Comp =" + comp +
        " | Jump = " + jump +
        " | Machine = " + machineCode + "]";
    }
}
