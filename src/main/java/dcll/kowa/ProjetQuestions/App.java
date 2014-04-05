package dcll.kowa.ProjetQuestions;

import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityQuestionService;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityReaderException;
/**
 * Point d'entré de l'application
 * @author thegame
 *
 */
public final class App {
	/**
	 * Constructeur privé qui empeche l'instanciation de la classe
	 */
	private App() { }
	
	/**
	 * Point d'entré de l'application
	 * @param args apps arguments
	 * @throws WikiversityReaderException WikiversityReaderException
	 * @throws IOException IOExceptions
	 */
    public static void main(String[] args) throws WikiversityReaderException, IOException
    {
    	BasicConfigurator.configure();
    	
    	try {
    		WikiversityQuestionService questionService = new WikiversityQuestionService();
        
    		String maQuestion = "{Séléctionnez un des \n le bon truc" 
    				+ "| type=\"()\"}" 
    				+ "+ Clojure." 
    				+ "||FeedBack de Clojure" 
    				+ "- Java." 
    				+ "||FeedBack de Java" 
    				+ "+ Groovy." 
    				+ "- Scala. ";
    		
    		Question parsedQuestion = questionService.getQuestionFromGiftText(maQuestion);
    		int nbAnswerBlockList = parsedQuestion.getAnswerBlockList().size();
    		int nbTextBlockList = parsedQuestion.getBlockList().size();
    		
    		/*Affichage des résultats du parsing */
    		
    		System.out.print("\n\nTitre: ");
    		System.out.println(parsedQuestion.getTitle());
    		System.out.println("\n----------------\n\n");
    		System.out.println("Answer List: \n---------\n");
    		for (int i = 0; i < nbAnswerBlockList; i++) {
    			int nbAnswerList = parsedQuestion.getAnswerBlockList().get(i).getAnswerList().size();
    			for (int j = 0; j < nbAnswerList; j++) {
    				System.out.println("Reponse: " + parsedQuestion.getAnswerBlockList().get(i).getAnswerList().get(j).getTextValue());
    				System.out.println("Son FeedBack: " + parsedQuestion.getAnswerBlockList().get(i).getAnswerList().get(j).getFeedBack());
    				System.out.println("Son ID: " + parsedQuestion.getAnswerBlockList().get(i).getAnswerList().get(j).getIdentifier());
    			}
    		}
    		
    		System.out.println("\n\nText List: \n---------------\n");
    		for (int i = 0; i < nbTextBlockList - 1; i++) {
    			System.out.println("Text N°" + i + ":" + parsedQuestion.getTextBlockList().get(i).getText());
    		}
    		
    		System.out.println("\n--------------\nType: " + parsedQuestion.getQuestionType().toString());
    		
    	} catch (Exception e) {
    		System.out.println(e.toString());
    	}
    }
}
