/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnathan Hernandez
CSCE 315
2/29/2020
 */
public class jdbcpostgreSQLGUI {
  public static void main(String args[]) {
    //Open frame for welcome user prompt
    welcomeFrame wF = new welcomeFrame();
  }//end main
}//end Class


//TODO List

//Brandon
//Add autofill for the value text box
//Add second row of combo boxes for union and intersection querying
//Add extra combobox for common questions like top player, top team, etc.
//Break down results from tables into English (EX: Searching for all plays with attempts returns only IDs and boolean values)
//  *Note: Might add a selection box to switch from "For" or "Against" to search for top stats on the 
//  given team or against the given team.
//Figure out how to compress to .jar executable file
//Add more constructors for the mainFrame to allow saving states and reloading them.
//Add tabs at top similar to other programs to specify application settings like output file name, load/save states, log out/in, etc.

//Seth (If any of these tasks are too cumbersome I can pick up some of the load once the above is complete)
//Implement hometown table for stats about hometowns for bonus
//Implement weighted algorithm for strength of schedules in question 3 and 4 and 5
//  Possibly use the differential in score and team conference to give value to teams.
//Create graph algorithms for chaining analysis in questions 1 and 2
//Add SQL scripts to pgAdmin for creation of new data values to reduce java load

//Either (If someone figures this out please do)
//If possible look into why JFrame.repaint() wasn't working.
//If unable to fix then add destruction of frame for each interaction so doesn't seem as
//  much of a bug and more of a feature.