package za.co.wethinkcode.streaming;

import za.co.wethinkcode.streaming.model.StreamSession;
import za.co.wethinkcode.streaming.model.WatchlistEntry;
import za.co.wethinkcode.streaming.service.RecommendationResult;
import za.co.wethinkcode.streaming.service.RecommendationService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StreamSession session = new StreamSession(
            "A001", "V001", "The Bear", 45, "Drama");
        WatchlistEntry entry = new WatchlistEntry(
            "A002", "V001", "Severance", "SciFi");

        RecommendationService svc = new RecommendationService();
        RecommendationResult result = svc.generateFromSession(
            session,
            List.of("Succession", "The White Lotus", "The Bear S2")
        );

        System.out.println("Session: " + session);
        System.out.println("Recommended: " + result.recommendedTitles());
        System.out.println("Watchlist entry: " + entry);
    }
}
