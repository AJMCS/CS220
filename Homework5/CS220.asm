// ----HEIGHT LOC----
//heightLoc = monitor + 106 pixels down.
// 1 pixel down = 32 registers away
// 32 * 106 = 3,392 

@3392
D=A //Grab the number you're moving down the monitor.
@SCREEN
D=A+D //D is now the address of the register where the height starts at.
@heightloc
M=D //Save the location to heightloc

// ----WIDTH LOC----
// 1 pixel accross = 1 bit
// Width should start 160 pixels into the screen.
// For every register holding 16bits, that would
// mean the widthloc should start 10 registers away 
// From the leftmost point of the screen

@heightloc
D=M
@10
D=A+D //D is now the address of the register where the width starts at.
@printloc
M=D //Save location to widthloc
@startloc
M=D



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
//             "C" Character                |
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|

//Loop C1 - Horizontal top bar of C

@i
M=0
@rowCounter
M=0

(LOOPC1)

@6
D=A
@i
D=M-D

@ENDLOOPC1
D;JEQ

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPC1
0;JMP

(ENDLOOPC1)


@rowCounter
D=M

@startloc
M=M+D

//Loop C2 - Left vertical bar of C

@i
M=0
@rowCounter
M=0

(LOOPC2)

@30
D=A
@i
D=M-D

@ENDLOOPC2
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPC2
0;JMP

(ENDLOOPC2)

@rowCounter
D=M

@startloc
M=M+D


//Loop C3 - Horizontal bottom bar of C

@i
M=0
@rowCounter
M=0

(LOOPC3)

@6
D=A
@i
D=M-D

@ENDLOOPC3
D;JEQ

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPC3
0;JMP

(ENDLOOPC3)







// "S" Character






@printloc  // A = 16384
D=M
@startloc
M=D+1
M=M+1

//Loop S1 - Horizontal top bar of S

@i
M=0
@rowCounter
M=0

(LOOPS1)

@6
D=A
@i
D=M-D

@ENDLOOPS1
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPS1
0;JMP

(ENDLOOPS1)

@rowCounter
D=M

@startloc
M=M+D

//Loop S2 - Left vertical bar of S

@i
M=0
@rowCounter
M=0

(LOOPS2)

@12
D=A
@i
D=M-D

@ENDLOOPS2
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPS2
0;JMP

(ENDLOOPS2)

@rowCounter
D=M

@startloc
M=M+D

//Loop S3 - Horizontal middle bar of S

@i
M=0
@rowCounter
M=0

(LOOPS3)

@6
D=A
@i
D=M-D

@ENDLOOPS3
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPS3
0;JMP

(ENDLOOPS3)

@rowCounter
D=M

@startloc
M=M+D

//Loop S4 - Right vertical bar of S

@i
M=0
@rowCounter
M=0

(LOOPS4)

@12
D=A
@i
D=M-D

@ENDLOOPS4
D;JGE

@rowCounter
D=M

@startloc
D=M+D
D=D+1

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPS4
0;JMP

(ENDLOOPS4)

@rowCounter
D=M

@startloc
M=M+D

//Loop S5 - Horizontal bottom bar of S

@i
M=0
@rowCounter
M=0

(LOOPS5)

@6
D=A
@i
D=M-D

@ENDLOOPS5
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPS5
0;JMP

(ENDLOOPS5)


// First "2" Character



@printloc  // A = 16384
D=M
@startloc
M=D+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
A=M

@rowCounter
D=M

@startloc
M=M+D


//Loop FT1 - Upper left vertical bar of first 2

@i
M=0
@rowCounter
M=0

(LOOPFT1)

@6
D=A
@i
D=M-D

@ENDLOOPFT1
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D

@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPFT1
0;JMP

(ENDLOOPFT1)

@printloc  // A = 16384
D=M
@startloc
M=D+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
A=M


//Loop FT2 - Horizontal top bar of first 2

@i
M=0
@rowCounter
M=0

(LOOPFT2)

@6
D=A
@i
D=M-D

@ENDLOOPFT2
D;JEQ

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPFT2
0;JMP

(ENDLOOPFT2)

@rowCounter
D=M

@startloc
M=M+D

//Loop FT3 - Right vertical bar of first 2

@i
M=0
@rowCounter
M=0

(LOOPFT3)

@12
D=A
@i
D=M-D

@ENDLOOPFT3
D;JGE

@rowCounter
D=M

@startloc
D=M+D
D=D+1

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPFT3
0;JMP

(ENDLOOPFT3)

@rowCounter
D=M

@startloc
M=M+D

//Loop FT4 - Horizontal middle bar of first 2

@i
M=0
@rowCounter
M=0

(LOOPFT4)

@6
D=A
@i
D=M-D

@ENDLOOPFT4
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPFT4
0;JMP

(ENDLOOPFT4)

@rowCounter
D=M

@startloc
M=M+D

//Loop FT5 - Lower left vertical bar of first 2

@i
M=0
@rowCounter
M=0

(LOOPFT5)

@12
D=A
@i
D=M-D

@ENDLOOPFT5
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPFT5
0;JMP

(ENDLOOPFT5)

@rowCounter
D=M

@startloc
M=M+D

//Loop FT6 - Horizontal bottom bar of first 2

@i
M=0
@rowCounter
M=0

(LOOPFT6)

@6
D=A
@i
D=M-D

@ENDLOOPFT6
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@4
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPFT6
0;JMP

(ENDLOOPFT6)







// Second "2" Character






@printloc  // A = 16384
D=M
@startloc
M=D+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
A=M

@rowCounter
D=M

@startloc
M=M+D


//Loop ST1 - Upper left vertical bar of second 2

@i
M=0
@rowCounter
M=0

(LOOPST1)

@6
D=A
@i
D=M-D

@ENDLOOPST1
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D

@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPST1
0;JMP

(ENDLOOPST1)

@printloc  // A = 16384
D=M
@startloc
M=D+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
A=M


//Loop ST2 - Horizontal top bar of second 2

@i
M=0
@rowCounter
M=0

(LOOPST2)

@6
D=A
@i
D=M-D

@ENDLOOPST2
D;JEQ

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPST2
0;JMP

(ENDLOOPST2)

@rowCounter
D=M

@startloc
M=M+D

//Loop ST3 - Right vertical bar of second 2

@i
M=0
@rowCounter
M=0

(LOOPST3)

@12
D=A
@i
D=M-D

@ENDLOOPST3
D;JGE

@rowCounter
D=M

@startloc
D=M+D
D=D+1

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPST3
0;JMP

(ENDLOOPST3)

@rowCounter
D=M

@startloc
M=M+D

//Loop ST4 - Horizontal middle bar of second 2

@i
M=0
@rowCounter
M=0

(LOOPST4)

@6
D=A
@i
D=M-D

@ENDLOOPST4
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPST4
0;JMP

(ENDLOOPST4)

@rowCounter
D=M

@startloc
M=M+D

//Loop ST5 - Lower left vertical bar of second 2

@i
M=0
@rowCounter
M=0

(LOOPST5)

@12
D=A
@i
D=M-D

@ENDLOOPST5
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPST5
0;JMP

(ENDLOOPST5)

@rowCounter
D=M

@startloc
M=M+D

//Loop ST6 - Horizontal bottom bar of second 2

@i
M=0
@rowCounter
M=0

(LOOPST6)

@6
D=A
@i
D=M-D

@ENDLOOPST6
D;JEQ

@rowCounter
D=M


@startloc
D=M+D

@temploc
M=D

@4
D=-A

@temploc
A=M
M=D

@temploc
M=M+1


@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOPST6
0;JMP

(ENDLOOPST6)





// "0" Character






@printloc  // A = 16384
D=M
@startloc
M=D+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
M=M+1
A=M


//Loop 01 - Horizontal top bar of 0

@i
M=0
@rowCounter
M=0

(LOOP01)

@6
D=A
@i
D=M-D

@ENDLOOP01
D;JEQ

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOP01
0;JMP

(ENDLOOP01)


@rowCounter
D=M

@startloc
M=M+D

//Loop 02 - Left and right vertical bars of 0

@i
M=0
@rowCounter
M=0

(LOOP02)

@30
D=A
@i
D=M-D

@ENDLOOP02
D;JGE

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@252
D=A

@temploc
A=M
M=D

@temploc
M=M+1

@256
D=-A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOP02
0;JMP

(ENDLOOP02)

@rowCounter
D=M

@startloc
M=M+D


//Loop 03 - Horizontal bottom bar of 0

@i
M=0
@rowCounter
M=0

(LOOP03)

@6
D=A
@i
D=M-D

@ENDLOOP03
D;JEQ

@rowCounter
D=M

@startloc
D=M+D

@temploc
M=D

@256
D=-A

@temploc
A=M
M=D

@temploc
M=M+1

@255
D=A

@temploc
A=M
M=D


@i
M=M+1

@32
D=A
@rowCounter
M=M+D

@LOOP03
0;JMP

(ENDLOOP03)

(END)
@END
0;JMP