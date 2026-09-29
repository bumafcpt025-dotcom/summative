package za.co.wethinkcode.streaming.service;

import java.util.Collections;
import java.util.List;

public class RecommendationResult {

    private final boolean successful;
    private final List<String> recommendedTitles;
    private final List<String> issues;

    private RecommendationResult(boolean successful,
                                  List<String> recommendedTitles,
                                  List<String> issues) {
        this.successful        = successful;
        this.recommendedTitles = List.copyOf(recommendedTitles);
        this.issues            = List.copyOf(issues);
    }

    public static RecommendationResult success(List<String> titles) {
        return new RecommendationResult(true, titles, List.of());
    }

    public static RecommendationResult failure(List<String> issues) {
        return new RecommendationResult(false, List.of(), issues);
    }

    public boolean           isSuccessful()       { return successful;        }
    public List<String>      recommendedTitles()  { return recommendedTitles; }
    public List<String>      issues()             { return issues;            }
}

