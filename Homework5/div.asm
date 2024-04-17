//INITIALIZE EVERYTHING
@R16 //A = 16
D=A  //A = D = 16
@R0
M=D  // RAM[0] = D = 16

@R3  //A = 3
D=A  //A = D = 3
@R1  
M=D  //RAM[0] = D = 3

@16 //A = 16
D=A //D = A

//-----------------------

(LOOP)
@R0
D=M // D = RAM[0]

@R1
D=D-M // D = RAM[0] - 3

@ENDLOOP
D;JLT // Is RAM[0] - 3 > 0?

@R2
M=M+1 // If true, increment R2

@R1
D=M // D = 3 (Divisor)

@R0
M=M-D // RAM[0] -= 3

@LOOP
0;JMP // Continue Loop

//-----------------------

(ENDLOOP)

(END) // Infinite Loop
@END
0;JMP

