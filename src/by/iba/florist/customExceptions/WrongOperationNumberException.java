package by.iba.florist.customExceptions;

public class WrongOperationNumberException extends Exception {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int number;
	    public int getNumber(){return number;}
	    public WrongOperationNumberException (String message, int num){
	     
	        super(message);
	        number = num;
	        
	    }
	
}
