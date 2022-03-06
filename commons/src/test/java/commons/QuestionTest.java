package commons;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuestionTest {

    @Test
    public void CheckConstructorMoreExpensive() {

        Question question = new QuestionMoreExpensive("Title Test", new ArrayList<>(), new ArrayList<>());
        assertEquals(question.getType(), 1);
        assertEquals(question.getTitle(), "Title Test");
    }

    @Test
    public void CheckConstructorHowMuchEnergy() {
        Question question = new QuestionHowMuch("Title Test", new ArrayList<>(), new ArrayList<>());
        assertEquals(question.getType(), 2);
        assertEquals(question.getTitle(), "Title Test");
    }

    @Test
    public void CheckToString() {
        Question question = new QuestionMoreExpensive("Title Test", new ArrayList<>(), new ArrayList<>());
        assertEquals(question.toString(), "QuestionMultipleChoice{id=0, type=1, title='Title Test', disabledPowerUps=[], answers=[]}");
    }

    @Test
    public void EqualsMoreExpensive() {
        Question question1 = new QuestionMoreExpensive("q1", new ArrayList<>(), new ArrayList<>());
        Question question2 = new QuestionMoreExpensive("q1", new ArrayList<>(), new ArrayList<>());
        assertEquals(question1, question2);
    }

    @Test
    public void EqualsMoreExpensiveFails() {
        Question question1 = new QuestionMoreExpensive("q1", new ArrayList<>(), new ArrayList<>());
        Question question2 = new QuestionMoreExpensive("q2", new ArrayList<>(), new ArrayList<>());
        assertNotEquals(question1, question2);
    }

    @Test
    public void EqualsHowMuch() {
        Question question1 = new QuestionHowMuch("q1", new ArrayList<>(), new ArrayList<>());
        Question question2 = new QuestionHowMuch("q1", new ArrayList<>(), new ArrayList<>());
        assertEquals(question1, question2);
    }

    @Test
    public void EqualsHowMuchFails() {
        Question question1 = new QuestionHowMuch("q1", new ArrayList<>(), new ArrayList<>());
        Question question2 = new QuestionHowMuch("q2", new ArrayList<>(), new ArrayList<>());
        assertNotEquals(question1, question2);
    }

    @Test
    public void EqualsSameTitleDifferentTypesOfQuestions() {
        Question question1 = new QuestionMoreExpensive("q1", new ArrayList<>(), new ArrayList<>());
        Question question2 = new QuestionHowMuch("q1", new ArrayList<>(), new ArrayList<>());
        assertNotEquals(question1, question2);
    }


}
