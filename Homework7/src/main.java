import java.io.FileNotFoundException;

public class main 
{

    public static final String PROGRAM_NAME = "Max";
    public static void main(String[] args)
    {

        //------ Brief Java Refresher ------\\

        briefJavaRefresher1();

        briefJavaRefresher2();

        briefJavaRefresher3();
        

        //------ Test First Pass of Assembly File ------\\

        try
        {
            AssemblyFileParser afp = new AssemblyFileParser(PROGRAM_NAME + ".asm");
            System.out.println("Cleaned Assembly Code:");
            System.out.println(afp);
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }




    //------ Methods ------\\


    //Java Refresher 1: How would you clean (remove whitespace and comments) from the following String?
    public static void briefJavaRefresher1()
    {
        String code = " O;JMP //unconditional jump   ";

        int commentIndex = code.indexOf("//");
        if (commentIndex != -1)
        {
            code = code.substring(0, commentIndex);
        }

        code.trim();

        System.out.println(code + "\n\n");
    }


    //Java Refresher 2: How would you extract the JMP from the cleanLine string below? Write the Java code to do so
    public static void briefJavaRefresher2()
    {
        //Initializing variables
        String cleanLine = "0;JMP";
        int indexOfColon = cleanLine.indexOf(";");
        String jump = null;


        //Collect Substring
        if(indexOfColon != -1)
        {
            cleanLine = cleanLine.substring(indexOfColon + 1);
        }

        //Print
        System.out.println(cleanLine+ "\n\n");
    }


    //Java Refresher 3: How would you extract the destination (dest) and computation (comp) part of this C‐instruction below?
    public static void briefJavaRefresher3()
    {
        //Initializeing variables
        String code = "D=M;JGT";
        int indexOfEquals = code.indexOf("=");
        int indexOfColon = code.indexOf(";");
        String dest = null;
        String comp = null;


        //Collect Dest substring
        if(indexOfEquals != -1)
        {
            dest = code.substring(0, indexOfEquals);
        }


        //Collect Comp substring
        if(indexOfColon != -1)
        {
            comp = code.substring(indexOfEquals + 1, indexOfColon);
        }


        //Print
        System.out.println("Code:" + code + "\nDest: " + dest + "\nComp: " + comp + "\n\n");
    }
}