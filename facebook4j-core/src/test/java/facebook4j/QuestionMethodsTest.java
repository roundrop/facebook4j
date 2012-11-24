package facebook4j;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void getQuestionOptionVotes() throws Exception {
        ResponseList<QuestionVotes> questionVotesList = real.getQuestionOptionVotes("381779171905254");
        for (QuestionVotes questionVotes : questionVotesList) {
            System.out.println(questionVotes);
        }
        
    }

}
