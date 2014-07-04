Project 1 -- CS61BL, Summer 2014, University of California, Berkeley

Contributors: CS61BL Laura Carolina Rodriguez-Adjunta, Rong Zheng, 61BL Summer Staff

Description: Implementation of a simplified version of Dots app

Relevant Code: GUI.java, Board.java, Dot.java

Goal of Dots Game: To score as many points given a limited number of moves you can make.
I.e. You must connect at least 2 adjacent dots of the same color to make them disappear. 
Dots must be connected 1 at a time, and each dot selected after the first one must be
adjacent (up, down, left, right, != diagonal). If dots make closed shape, then all dots
of the same color will be removed. 

Rules: Dots must be connected 1 at a time, and each dot selected after the first one must be
adjacent (up, down, left, right, != diagonal). If dots make closed shape, then all dots
of the same color will be removed. 
One point is earned for each Dot you remove. After each turn, the remaining dots will 
drop if they are above and empty space left behind disappearing dots. 
Then, random dots will drop from above to fill remaining space. 
** A closed shape must consist of 4+ dots such that path reconnects to itself.  
