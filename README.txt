HOW TO RUN:
Right click src/jdbcpostgreSQLGUI.java in VS Code and select Run.  

THINGS TO LOOK OUT FOR:
This product isn't fully finished, so it suffers from a couple bugs. Firstly, the double/single 
drop down is ALWAYS reset when you select a new table name for either table1 or table2 (meaning it
should be the last thing you change if you're interested in doing a double query). 

What is a double query? Basically it's a query that matches one table with columns that equal a value
to the base table (table1) through foreign keys. The relationship can go either way; the code should 
be robust enough to handle that. 

If you don't see any output in the output box, make sure you've selected the right radio button 
at the top of the UI. 