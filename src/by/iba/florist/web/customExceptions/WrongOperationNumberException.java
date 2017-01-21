package by.iba.florist.web.customExceptions;

public class WrongOperationNumberException extends Exception {
	
		private int number;
	    public int getNumber(){return number;}
	    public WrongOperationNumberException (String message, int num){
	     
	        super(message);
	        number=num;
	        
	    }
	
}
