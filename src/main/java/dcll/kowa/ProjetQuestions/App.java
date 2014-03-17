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
        System.out.println( "Hello World!" );

        GiftQuestionService gqs = new GiftQuestionService();
       Question q =  gqs.getQuestionFromGiftText("{ Une application possible est la création d'une liste de questions VRAI/FAUX : | type=\"()\" }"
       				+ "| VRAI | FAUX "
       				+ "-+ Cette extension "
       				+ " est pleine de bugs.");
       System.out.println(q.getQuestionType());
       		//System.out.println(q.getQuestionType());
       		
       		
    }
}
