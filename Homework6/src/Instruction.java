public abstract class Instruction
{
    protected String assemblyCode;
    protected String machineCode;
    
    //------ Getters ------\\

    public String getMachineCode() 
    {
        return machineCode;
    }
    
    public String getAssembyCode() 
    {
        return assemblyCode;
    }


    //------ Setters ------\\
    
    public void setMachineCode(String machineCode) 
    {
        this.machineCode = machineCode;
    }

    public void setAssembyCode(String assembyCode) 
    {
        this.assemblyCode = assembyCode;
    }


}