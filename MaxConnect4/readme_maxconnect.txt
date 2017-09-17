Name: Hemakumar Gokulakannan
ID:   1001415255
-----------------------------------------------------------
CODE STRUCTURE:

MaxConnect.java: Checks the arguments of the program and calls methods of GameBoard.java and Aiplayer.java to play the next move.

GameBoard.java: Contains methods to read the input file, get current and opponent player score,updaate the board state, evaluate the board,print the current board state to either file or Stdout.

AIPlayer.java: contains methods implementing alpha-beta minimax depth limited search to play the next move and returning the column id to play.

1) Once the code is executed, program checks whether it is interactive or one-move mode
2) if it is interactive mode and current-next, and maxconnect.java get the current turn and current state from the input file and calls findBestplay() method of AIplayer to find the best column to place.
3) Once the next move is recieved, maxconnect.java will call the playPiece() method to update the game state. 
4) And prompts for the human for the next move.
5) if it is interactive mode and human-next, maxconnect.java prompts for the human move first and then repeats with same process mentioned above points(2)(3)(4)  until the board is empty.  
6) if it is one-move mode, maxconnect reads the input file to get the current state and turn, then calls findBestplay() method of AIplayer to find the best column to place.
7)  Once the next move is received, maxconnect.java will call the playPiece() method to update the game state and print in the output file mentioned.

-----------------------------------------------------------
Programming Language used:

Java

Algorithm implemented: 
depth-limited minimax with alpha-beta pruning
------------------------------------------------------------
HOW TO RUN THE CODE

compile the code:

javac maxconnect4.java GameBoard.java AiPlayer.java

run the code:

java maxconnect4 one-move [input_file] [output_file] [depth]

or
java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]

note: provide the full path of the input/output file, if it is present the same directory as the java files.

-------------------------------------------------------------

Do you want to participate in the tournament :

Yes

------------------------------------------------------------


  