//SETUP
//Step 1: Get all parameters for the CS220 print screen saved in templocs
//NEEDED: @widthloc, @heightloc

// ----HEIGHT LOC----
//heightLoc = monitor + 104 pixels down.
// 1 pixel down = 32 registers away
// 32 * 104 = 3,328

@3328
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
@5
D=A+D //D is now the address of the register where the width starts at.
@widthloc
M=D //Save location to widthloc



// "C" Character

@widthloc
A=M
M=-1
A=A+1
M=-1




// "S" Character


// "2" Character


// "2" Character


// "0" Character