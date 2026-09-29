package za.co.wethinkcode.streaming.service;

import za.co.wethinkcode.streaming.model.StreamSession;
import za.co.wethinkcode.streaming.model.ViewerActivity;
import za.co.wethinkcode.streaming.model.WatchlistEntry;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {

    /**
     * CODE SMELL 1 — Boolean expression soup.
     *
     * This method chains multiple conditions into a single compound boolean
     * expression on one line, making it very hard to read, reason about,
     * or extend safely.
     *
     * TODO Q2.3: Identify the smell, refactor it, write your justification
     * in answers.txt (Q2.3.1 and Q2.3.3).
     */
    public boolean isEligibleForRecommendations(String viewerId,
                                                 List<ViewerActivity> activities,
                                                 boolean accountActive,
                                                 boolean regionSupported,
                                                 int minimumActivityCount) {
        if (viewerId != null && !viewerId.isBlank() && accountActive && regionSupported && activities != null && activities.size() >= minimumActivityCount && activities.stream().anyMatch(ViewerActivity::countsTowardRecommendations)) {
            return true;
        }
        return false;
    }

    /**
     * CODE SMELL 2 — Arrow code / deeply nested conditionals.
     *
     * This method validates a stream session before generating recommendations.
     * The validation logic is nested three levels deep, burying the main path
     * and making each rule hard to read or test independently.
     *
     * TODO Q2.3: Identify the smell, refactor it, write your justification
     * in answers.txt (Q2.3.1 and Q2.3.3).
     */
    public RecommendationResult generateFromSession(StreamSession session,
                                                     List<String> catalogue) {
        List<String> issues = new ArrayList<>();

        if (session != null) {
            if (session.title() != null && !session.title().isBlank()) {
                if (session.isCompletedView()) {
                    if (catalogue != null && !catalogue.isEmpty()) {
                        List<String> recommendations = new ArrayList<>();
                        for (String title : catalogue) {
                            if (!title.equals(session.title())
                                    && title.toLowerCase().contains(
                                            session.genre().toLowerCase())) {
                                recommendations.add(title);
                            }
                        }
                        if (recommendations.isEmpty()) {
                            issues.add("No catalogue titles match genre: " + session.genre());
                        } else {
                            return RecommendationResult.success(recommendations);
                        }
                    } else {
                        issues.add("Catalogue is empty or null");
                    }
                } else {
                    issues.add("Session too short to generate recommendations");
                }
            } else {
                issues.add("Session has no title");
            }
        } else {
            issues.add("Session is null");
        }

        return RecommendationResult.failure(issues);
    }
}
