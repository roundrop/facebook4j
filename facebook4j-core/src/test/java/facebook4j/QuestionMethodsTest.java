package facebook4j;

public class QuestionMethodsTest {

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
