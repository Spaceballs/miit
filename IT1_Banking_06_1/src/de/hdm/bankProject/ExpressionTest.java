package de.hdm.bankProject;



/**
  * Beschreibung.
  *
  * @version 1.0 vom 13.11.2004
  * @author CR
  */

public class ExpressionTest {

  public static void main(final String[] args) {
     int i;

     i = 10;
     System.out.println("Vor der Modifikation ist der Wert " + (i--%5>0));

     i = 10;  // Achtung: der Wert von i muss wieder auf 10 gesetzt werden!
     System.out.println("Nach der Umschreibung ist der Wert " + ((i-- % 5) > 0));
  }
}

