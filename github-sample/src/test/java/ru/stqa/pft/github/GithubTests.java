package ru.stqa.pft.github;

import com.jcabi.github.*;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("f333b095158db7ad59235ff34ff66ab82c98ab79");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("anotherplot", "java_course")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
