public class AInstruction extends Instruction
{

    public AInstruction(String code)
    {
        //Initialize variables
        int address;


        //------ Exception Handling ------\\

        //If code does not start with an @ symbol
        if(!code.startsWith("@"))
        {
            throw new IllegalArgumentException("A instructions must start with an @ symbol");
        }

        //If code does not have more than 1 character in that line.
        if(code.length() < 2)
        {
            throw new IllegalArgumentException("A instructions must be followed with an address location");
        }

        
        //------ Update Assembly and Machine Code ------\\

        //Update assembly code
        assemblyCode = code;

        //Update machine code
        address = Integer.parseInt(assemblyCode.substring(1));
        machineCode = decimalToBinary(address);
    }


    public String decimalToBinary(int address)
    {
        //Initialize variables
        StringBuilder binary = new StringBuilder(16);

        //Convert from decimal to binary add append to string
        while(address != 0)
        {
            binary.insert(0, address % 2);

            address /= 2;

        }

        //If the address is less than 16digits, add 0 to the front
        while(binary.length() < 16)
        {
            binary.insert(0, "0");
        }

        //Return binary string
        return binary.toString();
    }

    @Override
    public String toString()
    {
        return "A Instruction: [Assembly = " + assemblyCode + " | Machine = " + machineCode + "]";
    }

}