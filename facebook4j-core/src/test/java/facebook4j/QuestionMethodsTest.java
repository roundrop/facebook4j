package facebook4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.Question.Option;

public class QuestionMethodsTest extends FacebookTestBase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getQuestions_me() throws Exception {
        // require page access token
        ResponseList<Question> questions = real.getQuestions();
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    @Test
    public void getQuestions_id() throws Exception {
        String pageId = "137246726435626";
        ResponseList<Question> questions = real.getQuestions(pageId);
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    @Test
    public void getQuestionOption() throws Exception {
        ResponseList<Option> page1 = real.getQuestionOptions("381779171905254", new Reading().limit(1));
        Option option1 = page1.get(0);
        System.out.println(option1);
        Paging<Option> paging = page1.getPaging();
        ResponseList<Option> page2 = real.fetchNext(paging);
        Option option2 = page2.get(0);
        System.out.println(option2);
        ResponseList<Option> page3 = real.fetchNext(page2.getPaging());
        Option option3 = page3.get(0);
        System.out.println(option3);
        
        ResponseList<Option> _page2 = real.fetchPrevious(page3.getPaging());
        assertThat(_page2, is(page2));
        ResponseList<Option> _page1 = real.fetchPrevious(_page2.getPaging());
        assertThat(_page1, is(page1));
    }

    @Test
    public void getQuestionOptionVotes() throws Exception {
        ResponseList<QuestionVotes> questionVotesList = real.getQuestionOptionVotes("381779171905254");
        for (QuestionVotes questionVotes : questionVotesList) {
            System.out.println(questionVotes);
        }
    }

}
