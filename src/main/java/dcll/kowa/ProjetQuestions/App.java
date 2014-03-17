package dcll.kowa.ProjetQuestions;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;

import dcll.kowa.ProjetQuestions.impl.gift.GiftQuestionService;
import dcll.kowa.ProjetQuestions.impl.gift.GiftReaderException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws GiftReaderException, IOException
    {
    	BasicConfigurator.configure();
    	
    	try
    	{
    		GiftQuestionService questionService = new GiftQuestionService();
        
    		String maQuestion = "{Sélectionnez les langages dynamiques | type=\"[]\"}"
    				+ "+ Clojure."
    				+ "- Java."
    				+ "+ Groovy."
    				+ "- Scala.";
    		
    		Question parsedQuestion = questionService.getQuestionFromGiftText(maQuestion);
    		System.out.println(parsedQuestion.getTitle());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    }
}