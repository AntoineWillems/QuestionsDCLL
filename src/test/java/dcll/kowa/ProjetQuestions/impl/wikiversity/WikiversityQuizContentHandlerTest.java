package dcll.kowa.ProjetQuestions.impl.wikiversity;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultQuiz;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityQuizContentHandler;

import junit.framework.TestCase;

public class WikiversityQuizContentHandlerTest extends TestCase{
	
	private WikiversityQuizContentHandler wqch;
	
	protected void setUp() throws Exception {
		super.setUp();
		wqch = new WikiversityQuizContentHandler();
	}
	
	@Test
	public void testGetQuizContentHandler(){
		assertEquals(null,wqch.getQuiz());
	}
	
	@Test
	public void testOnStartQuiz(){
		wqch.onStartQuiz();
	}
	
	@Test
	public void testOnEndQuiz(){
		wqch.onEndQuiz();
	}
	
	@Test
	public void testOnStartQuestion(){
		wqch.onStartQuestion();
	}
	
	@Test
	public void testOnEndQuestion(){
		//wqch.onEndQuestion();
	}
	
	@Test
	public void testOnStartTitle(){
		wqch.onStartTitle();
	}
	
	@Test
	public void testOnEndTitle(){
		//wqch.onEndTitle();
	}
	
	@Test
	public void testOnStartAnswerBlock(){
		wqch.onStartAnswerBlock();
	}
	
	@Test
	public void testOnEndAnswerBlock(){
		//wqch.onEndAnswerBlock();
	}
	
	@Test
	public void testOnStartAnswer(){
		wqch.onStartAnswer("+");
		wqch.onStartAnswer("-");
	}
	
	@Test
	public void testOnEndtAnswer(){
		//wqch.onEndAnswer();
	}
	
	@Test
	public void testOnStartAnswerCredit(){
		wqch.onStartAnswerCredit();
	}
	
	@Test
	public void testOnEndAnswerCredit(){
		wqch.onEndAnswerCredit();
	}
	
	@Test
	public void testOnStartAnswerFeedBack(){
		wqch.onStartAnswerFeedBack();
	}
	
	@Test
	public void testOnEndAnswerFeedBack(){
		wqch.onEndAnswerFeedBack();
	}
	
	@Test
	public void testOnString(){
		wqch.onStartTitle();
		wqch.onString("");
	}
	
	@Test
	public void testOnString1(){
		wqch.onStartAnswerCredit();
		//wqch.onString("");
	}
	
	@Test
	public void testOnString2(){
		wqch.onStartAnswerFeedBack();
		//wqch.onString("");
	}
	
	@Test
	public void testOnString3(){
		wqch.onStartAnswer("");
		wqch.onString("");
	}
	
	@Test
	public void testOnString4(){
		wqch.onStartQuestion();
		wqch.onString("type=\"()\"");
	}
	
	@Test
	public void testOnString5(){
		wqch.onStartQuestion();
		wqch.onString("blabla");
	}
	
	
	
	
}
