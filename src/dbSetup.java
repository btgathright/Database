//This is a database setup object responsible for holding usernames and passwords
//that the user inputs in the welcomeFrame.

public class dbSetup
{
  public String user;
  public String pswd;

  dbSetup(String alphaVal, String betaVal)
  {
    user = alphaVal;
    pswd = betaVal;
  }
}//end class
