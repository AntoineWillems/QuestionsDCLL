package dcll.kowa.ProjetQuestions;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;

import dcll.kowa.ProjetQuestions.impl.gift.GiftQuestionService;
import dcll.kowa.ProjetQuestions.impl.gift.GiftReaderException;
import dcll.kowa.ProjetQuestions.impl.gift.WikiQuestionService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws GiftReaderException, IOException
    {
    	BasicConfigurator.configure();
        System.out.println( "Hello World!" );

        WikiQuestionService wqs = new WikiQuestionService();
       Question q =  wqs.getQuestionFromGiftText("{ Une + application ()possible est la[] création d'une liste de questions |type=\"[]\"}" +
       		" - [Vrai)" +
       		" + False}" +
       		" + {Test");
       System.out.println("Type de la question: " + q.getQuestionType());
       for(int i=0;i<q.getTextBlockList().size();i++){
    	   TextBlock t = q.getTextBlockList().get(i);
    	   System.out.println("Question: " + t.getText());
       }
       
      for(int j=0;j< q.getAnswerBlockList().size();j++){
    	  AnswerBlock ab = q.getAnswerBlockList().get(j);
    	  for(int k=0;k<ab.getAnswerList().size();k++){
    		  Answer a = ab.getAnswerList().get(k);
    		  System.out.println("Reponse n°" +a.getIdentifier() + " : " + a.getTextValue());
    	  }
      }
       		//System.out.println(q.getQuestionType());
       		
       		
    	
    	/*try
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
    	}*/

    }
}
