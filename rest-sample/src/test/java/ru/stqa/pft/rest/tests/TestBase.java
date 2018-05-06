package ru.stqa.pft.rest.tests;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;


public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Get(app.getProperty("rest.baseUrl") + "/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonArray issueInfo = parsed.getAsJsonObject().getAsJsonArray("issues");
        int state = 0;

        for (JsonElement issueDetail : issueInfo) {
            JsonObject jsonObject = issueDetail.getAsJsonObject();
            state = jsonObject.get("state").getAsInt();
        }

        if (state != 3) {
            return true;
        } else return false;


    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("rest.auth"), app.getProperty("rest.pass"));

    }


    protected Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Get(app.getProperty("rest.baseUrl") + "/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    protected int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Post(app.getProperty("rest.baseUrl") + "/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }


}
