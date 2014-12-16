package th.go.motorcycles.app.enjoy.exception;


public class EnjoyException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	Throwable exceptionCause = null;
	
	public EnjoyException(String message) {
  		super(message);
  		this.message = message;
  	}
	
    public EnjoyException(String messages, Throwable exception) {
        super(messages, exception);
        
        this.message	 		= messages;
        this.exceptionCause 	= exception;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
    public void printStackTrace() {
        if (this.exceptionCause != null) {
        	this.exceptionCause.printStackTrace();
        }
    }
}
