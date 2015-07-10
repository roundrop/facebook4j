package facebook4j;

import facebook4j.internal.http.RequestMethod;
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Date;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
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

    public static class createQuestion extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            QuestionUpdate questionUpdate = new QuestionUpdate("What is your favorite programming language?")
                    .option("Java").option("PHP").option("Ruby").option("C#")
                    .allowNewOptions(true)
                    .published(false)
                    .scheduledPublishTime(new Date(new Date().getTime() + 1000 * 60 * 15));
            String actual = facebook.createQuestion(questionUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/questions")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            QuestionUpdate questionUpdate = new QuestionUpdate("What is your favorite programming language?")
                    .option("Java").option("PHP").option("Ruby").option("C#")
                    .allowNewOptions(true)
                    .published(false)
                    .scheduledPublishTime(new Date(new Date().getTime() + 1000 * 60 * 15));
            String actual = facebook.createQuestion("137246726435626", questionUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/questions")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class getQuestion extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/question/question.json");
            Question actual = facebook.getQuestion("154498061377159");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/154498061377159")));

            assertThat(actual.getId(), is("154498061377159"));
            assertThat(actual.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual.getFrom().getId(), is("137246726435626"));
            assertThat(actual.getFrom().getCategory(), is("Software"));
            assertThat(actual.getFrom().getName(), is("F4J"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:22+0000")));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-03-18T10:34:22+0000")));
            assertThat(actual.getOptions().get(0).getId(), is("260686110733539"));
            assertThat(actual.getOptions().get(0).getName(), is("C#"));
            assertThat(actual.getOptions().get(0).getVoteCount(), is(0));
            assertThat(actual.getOptions().get(0).getFrom().getId(), is("137246726435626"));
            assertThat(actual.getOptions().get(0).getFrom().getCategory(), is("Software"));
            assertThat(actual.getOptions().get(0).getFrom().getName(), is("F4J"));
            assertThat(actual.getOptions().get(0).getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:18+0000")));
            assertThat(actual.getOptions().get(1).getId(), is("320634584706152"));
            assertThat(actual.getOptions().get(1).getName(), is("Ruby"));
            assertThat(actual.getOptions().get(1).getVoteCount(), is(0));
            assertThat(actual.getOptions().get(1).getFrom().getId(), is("137246726435626"));
            assertThat(actual.getOptions().get(1).getFrom().getCategory(), is("Software"));
            assertThat(actual.getOptions().get(1).getFrom().getName(), is("F4J"));
            assertThat(actual.getOptions().get(1).getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:19+0000")));
            assertThat(actual.getOptions().get(2).getId(), is("302535046540868"));
            assertThat(actual.getOptions().get(2).getName(), is("PHP"));
            assertThat(actual.getOptions().get(2).getVoteCount(), is(0));
            assertThat(actual.getOptions().get(2).getFrom().getId(), is("137246726435626"));
            assertThat(actual.getOptions().get(2).getFrom().getCategory(), is("Software"));
            assertThat(actual.getOptions().get(2).getFrom().getName(), is("F4J"));
            assertThat(actual.getOptions().get(2).getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:20+0000")));
            assertThat(actual.getOptions().get(3).getId(), is("462870730453151"));
            assertThat(actual.getOptions().get(3).getName(), is("Java"));
            assertThat(actual.getOptions().get(3).getVoteCount(), is(0));
            assertThat(actual.getOptions().get(3).getFrom().getId(), is("137246726435626"));
            assertThat(actual.getOptions().get(3).getFrom().getCategory(), is("Software"));
            assertThat(actual.getOptions().get(3).getFrom().getName(), is("F4J"));
            assertThat(actual.getOptions().get(3).getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:21+0000")));
            assertThat(actual.getOptions().getPaging().getNext().toString(), is("https://graph.facebook.com/154498061377159/options?access_token=access_token&limit=25&offset=25&__after_id=462870730453151"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/question/question_q.json");
            Question actual = facebook.getQuestion("154498061377159", new Reading().fields("question"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/154498061377159")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "question"));

            assertThat(actual.getId(), is("154498061377159"));
            assertThat(actual.getQuestion(), is("What is your favorite programming language?"));
            assertThat(actual.getOptions().size(), is(0));
            assertThat(actual.getFrom(), is(nullValue()));
        }
    }

    public static class deleteQuestion extends MockFacebookTestBase {
        @Test
        public void delete() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteQuestion("155688711258094");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/155688711258094")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void delete_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteQuestion("155688711258094");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/155688711258094")));

            assertThat(actual, is(true));
        }
    }

    public static class getQuestionOptions extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/question/options.json");
            ResponseList<Question.Option> actuals = facebook.getQuestionOptions("154498061377159");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/154498061377159/options")));

            assertThat(actuals.size(), is(4));
            Question.Option actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("260686110733539"));
            assertThat(actual1.getName(), is("C#"));
            assertThat(actual1.getVoteCount(), is(0));
            assertThat(actual1.getFrom().getId(), is("137246726435626"));
            assertThat(actual1.getFrom().getCategory(), is("Software"));
            assertThat(actual1.getFrom().getName(), is("F4J"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:18+0000")));
            Question.Option actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("462870730453151"));
            assertThat(actual4.getName(), is("Java"));
            assertThat(actual4.getVoteCount(), is(0));
            assertThat(actual4.getFrom().getId(), is("137246726435626"));
            assertThat(actual4.getFrom().getCategory(), is("Software"));
            assertThat(actual4.getFrom().getName(), is("F4J"));
            assertThat(actual4.getCreatedTime(), is(iso8601DateOf("2013-03-18T10:34:21+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/question/options_vc.json");
            ResponseList<Question.Option> actuals = facebook.getQuestionOptions("154498061377159", new Reading().fields("vote_count"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/154498061377159/options")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "vote_count"));

            assertThat(actuals.size(), is(4));
            Question.Option actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("462870730453151"));
            assertThat(actual1.getName(), is(nullValue()));
            assertThat(actual1.getVoteCount(), is(1));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
            Question.Option actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("302535046540868"));
            assertThat(actual4.getName(), is(nullValue()));
            assertThat(actual4.getVoteCount(), is(0));
            assertThat(actual4.getFrom(), is(nullValue()));
            assertThat(actual4.getCreatedTime(), is(nullValue()));
        }
    }

    public static class addQuestionOption extends MockFacebookTestBase {
        @Test
        public void add() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.addQuestionOption("154498061377159", "Python");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/154498061377159/options")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("option", "Python"));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class getQuestionOptionVotes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/question/votes.json");
            ResponseList<QuestionVotes> actuals = facebook.getQuestionOptionVotes("154498061377159");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/154498061377159/options")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "votes"));

            assertThat(actuals.size(), is(5));
            QuestionVotes actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("462870730453151"));
            assertThat(actual1.getVotes().get(0).getId(), is("1234567890123456"));
            assertThat(actual1.getVotes().get(0).getName(), is("My Name"));
            assertThat(actual1.getVotes().getPaging().getNext().toString(), is("https://graph.facebook.com/462870730453151/votes?access_token=access_token&limit=5000&offset=5000&__after_id=100001568838021"));
            QuestionVotes actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("455320461233522"));
            assertThat(actual5.getVotes().size(), is(0));
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
