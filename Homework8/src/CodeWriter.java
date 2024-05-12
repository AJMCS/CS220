package Homework8.src;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class CodeWriter 
{
    private PrintWriter fileWriter;
    private static final HashMap<String, String> commandMap;
    private static final HashMap<String, String> segmentMap;
    private static int labelCounter;


    static
    {
        commandMap = new HashMap(9);

        commandMap.put("add", "M=M+D");
        commandMap.put("sub", "M=M-D");
        commandMap.put("and", "M=M&D");
        commandMap.put("or", "M=M|D");
        commandMap.put("neg", "M=-M");
        commandMap.put("not", "M=!M");
        commandMap.put("eq", "JEQ");
        commandMap.put("gt", "JGT");
        commandMap.put("lt", "JLT");


        segmentMap = new HashMap(7);

        segmentMap.put("constant", null);
        segmentMap.put("local", "@LCL");
        segmentMap.put("argument", "@ARG");
        segmentMap.put("this", "@THIS");
        segmentMap.put("that", "@THAT");
        segmentMap.put("temp", "@R");
        
        labelCounter = 0;
       
    }

    public CodeWriter(String fileName) throws FileNotFoundException
    {
        fileWriter = new PrintWriter(fileName + ".asm");
        segmentMap.put("static", "@" + fileName + ".");
    }


    public void writeCode(String command, String segment, int index)
    {

        switch(command)
        {
            //pus/pop
            case "push":
            generatePushASMCode(command, segment, index);
            break;

            case "pop":
            generatePopASMCode(command, segment, index);
            break;

            //arithmetic-binary
            case "add":
            case "sub":
            case "and":
            case "or":
            
            popStack();
            fileWriter.println("A=A-1");
            fileWriter.println(commandMap.get(command));
            break;

            //arithmetic-unary
            case "neg":
            case "not":
            fileWriter.println("@SP");
            fileWriter.println("A=M-1");
            fileWriter.println(commandMap.get(command));
            break;

            //logical-jumps

            case "eq":
            case "gt":
            case "lt":

            popStack();
            fileWriter.println("A=A-1");
            fileWriter.println("D=M-D");
            fileWriter.println("@IFTRUE_" + labelCounter);
            fileWriter.println("D;" + commandMap.get(command));
            fileWriter.println("@SP");
            fileWriter.println(("A=M-1"));
            fileWriter.println("M=0");
            fileWriter.println("@END_" + labelCounter);
            fileWriter.println("0;JMP");
            
            fileWriter.println("(IFTRUE_" + labelCounter + ")");
            fileWriter.println("@SP");
            fileWriter.println(("A=M-1"));
            fileWriter.println("M=-1");
            fileWriter.println("(END_" + labelCounter + ")");

            labelCounter++;
            break;



        }

    }


    private void generatePopASMCode(String command, String segment, int index) {
        

        switch(segment)
        {
            case "constant":
            return;

            case "argument":
            case "local":
            case "this":
            case "that":

            fileWriter.println("@" + index);
            fileWriter.println("D=A");
            fileWriter.println(segmentMap.get(segment));
            fileWriter.println("D=M+D");
            fileWriter.println("@temploc");
            fileWriter.println("M=D");

            popStack();
            fileWriter.println("@temploc");
            fileWriter.println("A=M");
            fileWriter.println("M=D");
            break;

            case "static":
            case "pointer":
            case "temp":
            
            popStack();

            if("static".equals(segment))
            {
                fileWriter.println(segmentMap.get(segment) + index);
            }
            else if("pointer".equals(segment))
            {
                if(index == 0)
                {
                    fileWriter.println(segmentMap.get("this"));
                }
                else if(index == 1)
                {
                    fileWriter.println(segmentMap.get("that"));
                }
                    
            }
            else
            {
                fileWriter.println(segmentMap.get(segment) + (index + 5));
            }

            fileWriter.println("M=D");
            break;

        }
    }


    private void generatePushASMCode(String command, String segment, int index) {
        
        switch(segment)
        {
            case "constant":
            fileWriter.println("@" + index);
            fileWriter.println("D=A");
            break;

            case "argument":
            case "local":
            case "this":
            case "that":
            fileWriter.println("@" + index);
            fileWriter.println("D=A");
            fileWriter.println(segmentMap.get(segment));
            fileWriter.println("A=M+D");
            fileWriter.println("D=M");
            break;

            case "static":
            fileWriter.println(segmentMap.get(segment) + index);
            fileWriter.println("D=M");
            break;

            case "pointer":
            if(index == 0)
            {
                fileWriter.println(segmentMap.get("this"));
            }
            else
            {
                fileWriter.println(segmentMap.get("that"));
            }

            fileWriter.println("D=M");
            break;

            case "temp":
            fileWriter.println(segmentMap.get(segment) + (index + 5));
            fileWriter.println("D=M");
            break;
        }

        pushStack();
    }



    //------ Helper Methods ------\\

    private void popStack()
    {
        fileWriter.println("@SP");
        fileWriter.println("AM=M-1");
        fileWriter.println("D=M");

    }

    private void pushStack()
    {
        fileWriter.println("@SP");
        fileWriter.println("M=M+1");
        fileWriter.println("A=M-1");
        fileWriter.println("M=D");
    }


    public void close() {
        fileWriter.println("(INFINITE_LOOP)");
        fileWriter.println("@INFINITE_LOOP");
        fileWriter.println("0;JMP");
        fileWriter.close();

    }
    
}
