package priv.cai.jobapply.springmvc.dao;

public class DataAccessLayerException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5836797886203603067L;

	public DataAccessLayerException() {
    }

    public DataAccessLayerException(String message) {
        super(message);
    }

    public DataAccessLayerException(Throwable cause) {
        super(cause);
    }

    public DataAccessLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
