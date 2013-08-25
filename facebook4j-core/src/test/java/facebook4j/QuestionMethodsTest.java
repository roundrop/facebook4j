package facebook4j;

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class QuestionMethodsTest {

    public static class getQuestions extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/question/questions.json");
            ResponseList<Question> actuals = facebook.getQuestions();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/questions")));

            assertThat(actuals.size(), is(2));
            Question actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("300000000000001"));
            assertThat(actual1.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual1.getOptions().get(0).getId(), is("400000000000001"));
            assertThat(actual1.getOptions().get(0).getName(), is("Java"));
            assertThat(actual1.getOptions().get(0).getVoteCount(), is(1));
            assertThat(actual1.getOptions().get(0).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getOptions().get(0).getFrom().getName(), is("My Name"));
            assertThat(actual1.getOptions().get(0).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:33+0000")));
            assertThat(actual1.getOptions().get(2).getId(), is("400000000000003"));
            assertThat(actual1.getOptions().get(2).getName(), is("PHP"));
            assertThat(actual1.getOptions().get(2).getVoteCount(), is(0));
            assertThat(actual1.getOptions().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getOptions().get(2).getFrom().getName(), is("My Name"));
            assertThat(actual1.getOptions().get(2).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:32+0000")));
            assertThat(actual1.getOptions().getPaging().getNext().toString(), is("https://graph.facebook.com/300000000000001/options?access_token=access_token&limit=25&offset=25&__after_id=300000000000001"));
            Question actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("300000000000001"));
            assertThat(actual2.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("My Name"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual2.getOptions().get(0).getId(), is("400000000000001"));
            assertThat(actual2.getOptions().get(0).getName(), is("Java"));
            assertThat(actual2.getOptions().get(0).getVoteCount(), is(1));
            assertThat(actual2.getOptions().get(0).getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getOptions().get(0).getFrom().getName(), is("My Name"));
            assertThat(actual2.getOptions().get(0).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:33+0000")));
            assertThat(actual2.getOptions().get(2).getId(), is("400000000000003"));
            assertThat(actual2.getOptions().get(2).getName(), is("PHP"));
            assertThat(actual2.getOptions().get(2).getVoteCount(), is(0));
            assertThat(actual2.getOptions().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getOptions().get(2).getFrom().getName(), is("My Name"));
            assertThat(actual2.getOptions().get(2).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:32+0000")));
            assertThat(actual2.getOptions().getPaging().getNext().toString(), is("https://graph.facebook.com/300000000000001/options?access_token=access_token&limit=25&offset=25&__after_id=300000000000001"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/question/questions_q.json");
            ResponseList<Question> actuals = facebook.getQuestions(new Reading().fields("question"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/questions")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "question"));

            assertThat(actuals.size(), is(1));
            Question actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("300000000000001"));
            assertThat(actual1.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getOptions().size(), is(0));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/question/questions.json");
            ResponseList<Question> actuals = facebook.getQuestions("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/questions")));

            assertThat(actuals.size(), is(2));
            Question actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("300000000000001"));
            assertThat(actual1.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual1.getOptions().get(0).getId(), is("400000000000001"));
            assertThat(actual1.getOptions().get(0).getName(), is("Java"));
            assertThat(actual1.getOptions().get(0).getVoteCount(), is(1));
            assertThat(actual1.getOptions().get(0).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getOptions().get(0).getFrom().getName(), is("My Name"));
            assertThat(actual1.getOptions().get(0).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:33+0000")));
            assertThat(actual1.getOptions().get(2).getId(), is("400000000000003"));
            assertThat(actual1.getOptions().get(2).getName(), is("PHP"));
            assertThat(actual1.getOptions().get(2).getVoteCount(), is(0));
            assertThat(actual1.getOptions().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getOptions().get(2).getFrom().getName(), is("My Name"));
            assertThat(actual1.getOptions().get(2).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:32+0000")));
            assertThat(actual1.getOptions().getPaging().getNext().toString(), is("https://graph.facebook.com/300000000000001/options?access_token=access_token&limit=25&offset=25&__after_id=300000000000001"));
            Question actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("300000000000001"));
            assertThat(actual2.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("My Name"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2012-11-24T03:47:34+0000")));
            assertThat(actual2.getOptions().get(0).getId(), is("400000000000001"));
            assertThat(actual2.getOptions().get(0).getName(), is("Java"));
            assertThat(actual2.getOptions().get(0).getVoteCount(), is(1));
            assertThat(actual2.getOptions().get(0).getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getOptions().get(0).getFrom().getName(), is("My Name"));
            assertThat(actual2.getOptions().get(0).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:33+0000")));
            assertThat(actual2.getOptions().get(2).getId(), is("400000000000003"));
            assertThat(actual2.getOptions().get(2).getName(), is("PHP"));
            assertThat(actual2.getOptions().get(2).getVoteCount(), is(0));
            assertThat(actual2.getOptions().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getOptions().get(2).getFrom().getName(), is("My Name"));
            assertThat(actual2.getOptions().get(2).getCreatedTime(), is(iso8601DateOf("2012-11-24T03:47:32+0000")));
            assertThat(actual2.getOptions().getPaging().getNext().toString(), is("https://graph.facebook.com/300000000000001/options?access_token=access_token&limit=25&offset=25&__after_id=300000000000001"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/question/questions_q.json");
            ResponseList<Question> actuals = facebook.getQuestions("1234567890123456", new Reading().fields("question"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/questions")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "question"));

            assertThat(actuals.size(), is(1));
            Question actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("300000000000001"));
            assertThat(actual1.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getOptions().size(), is(0));
        }

    }

/*
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

    @Test
    public void createQuestion() throws Exception {
        // require manage_pages permission
        // replace to your page id
        String pageId = "137246726435626";
        QuestionUpdate questionUpdate = new QuestionUpdate("What is your favorite programming language?")
                .option("Java").option("PHP").option("Ruby").option("C#")
                .allowNewOptions(true)
                .published(false)
                .scheduledPublishTime(new Date(new Date().getTime() + 1000 * 60 * 15));
        String questionId = real.createQuestion(pageId, questionUpdate);
        assertThat(questionId, is(notNullValue()));
        Question question = real.getQuestion(questionId);
        assertThat(question.getId(), is(questionId));
        assertThat(question.getOptions().size(), is(4));
    }

    @Test
    public void createQuestion_me() throws Exception {
        // require page access_token
        QuestionUpdate questionUpdate = new QuestionUpdate("What is your favorite programming language?")
                .option("Java").option("PHP").option("Ruby").option("C#")
                .allowNewOptions(true);
        String questionId = real.createQuestion(questionUpdate);
        assertThat(questionId, is(notNullValue()));
        Question question = real.getQuestion(questionId);
        assertThat(question.getId(), is(questionId));
        assertThat(question.getOptions().size(), is(4));
    }
*/
}
