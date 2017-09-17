--------------------------------------
Name: Hemakumar Gokulakannan
ID: 1001415255
---------------------------------------
Programming Language Used: Java
---------------------------------------
Program Structure:

The program has two parts:
1) CheckTrueFalse.java
2) LogicalExpression.java

LogicalExpression:
1) Contains methods to cast each line of the input into a vector of objects.
2) It has PLTrue() method which iterates through the input expressions objects and evalutes the model of symbols to return true or false.
 

CheckTrueFalse:
1) Reads the input wumpus rules and additional knowledge base files and each line is converted as an object of LogicalExpression and if the each line has sub expressions each subexpression will be an object  of LogicalExpression. 
2) Once all the input files are read, the TTEntail() method is called which models the symbols and when  values for all symbols are produced, PLTrue() method is called to check whether the model of the calling object(either KB or statement) returns true or false.
3) calls  TTEntail() for both statement and negation of statement and produces the result in the result.txt file.


--------------------------------------------

Program compilation:

compile:
javac *.java


run format:
java CheckTrueFalse wumpus_rules.txt <additional_KB.txt> <statement.txt>


note: all the input file if just provided without a path, the program expects the file to be in the same directory as java file. And the output will be produced in result.txt file in the same location of the program files.  