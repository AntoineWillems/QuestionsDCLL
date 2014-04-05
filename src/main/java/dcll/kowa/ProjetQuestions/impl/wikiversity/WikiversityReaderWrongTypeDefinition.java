package dcll.kowa.ProjetQuestions.impl.wikiversity;

/**
 * 
 * @author wallart
 *
 */
public class WikiversityReaderWrongTypeDefinition extends WikiversityReaderException {
	
	/**
	 * WikiversityReaderWrongTypeDefinition
	 */
	public WikiversityReaderWrongTypeDefinition() {
		super("Must be : type=\"()\"");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * WikiversityReaderWrongTypeDefinition
	 * @param message the error message
	 */
	public WikiversityReaderWrongTypeDefinition(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
