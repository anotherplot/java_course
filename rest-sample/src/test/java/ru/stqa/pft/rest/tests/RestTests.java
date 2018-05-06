package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests extends TestBase {

    @Test(enabled = false)
    public void testCreateIssue() throws IOException {

        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("hello test issue").withDescription("new test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }

    @Test
    public void testBugFix() throws IOException {
        skipIfNotFixed(1201);
        System.out.println("test is starting, bug was fixed");

    }


    @Test
    public void testBugNotFixed() throws IOException {
        skipIfNotFixed(1);
        System.out.println("test is starting, bug was fixed");

    }


}
