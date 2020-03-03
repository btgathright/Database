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

//Add autofill for the value text box
//Add second row of combo boxes for union and intersection querying
//Add scroll bar or better output to the GUI.
//Add extra combobox for common questions like top player, top team, etc.
//Break down results from tables into English (EX: Searching for all plays with attempts returns only IDs and boolean values)
//  *Note: Might add a selection box to switch from "For" or "Against" to search for top stats on the 
//  given team or against the given team.
//Possible pull results into object and open smaller panel under the options panel for deeper querying.
//  EX: Like above search, allow the user to sort from here by each column from the results. Similar to excel sort
//Figure out how to compress to .jar executable file
//Add more constructors for the mainFrame to allow saving states and reloading them.
//Add tabs at top similar to other programs to specify application settings like output file name, load/save states, log out/in, etc.
//Address the questions required in the deliverables

//If possible look into why JFrame.repaint() wasn't working.
//If unable to fix then add destruction of frame for each interaction so doesn't seem as
//  much of a bug and more of a feature.