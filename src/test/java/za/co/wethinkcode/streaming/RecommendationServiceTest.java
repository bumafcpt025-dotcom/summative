package za.co.wethinkcode.streaming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.streaming.model.StreamSession;
import za.co.wethinkcode.streaming.model.ViewerActivity;
import za.co.wethinkcode.streaming.model.WatchlistEntry;
import za.co.wethinkcode.streaming.service.RecommendationResult;
import za.co.wethinkcode.streaming.service.RecommendationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecommendationServiceTest {

    private RecommendationService service;
    private List<String>          catalogue;
    private List<ViewerActivity>  activities;

    @BeforeEach
    void setUp() {
        service   = new RecommendationService();
        catalogue = List.of("The Bear S2", "Succession", "Severance", "The White Lotus");
        StreamSession session = new StreamSession("A001","V001","The Bear",45,"Drama");
        activities = List.of(session);
    }

    // ── isEligibleForRecommendations ──────────────────────────

    @Test
    void eligible_whenAllConditionsMet() {
        assertThat(service.isEligibleForRecommendations(
                "V001", activities, true, true, 1)).isTrue();
    }

    @Test
    void notEligible_whenViewerIdIsNull() {
        assertThat(service.isEligibleForRecommendations(
                null, activities, true, true, 1)).isFalse();
    }

    @Test
    void notEligible_whenViewerIdIsBlank() {
        assertThat(service.isEligibleForRecommendations(
                "  ", activities, true, true, 1)).isFalse();
    }

    @Test
    void notEligible_whenAccountInactive() {
        assertThat(service.isEligibleForRecommendations(
                "V001", activities, false, true, 1)).isFalse();
    }

    @Test
    void notEligible_whenRegionNotSupported() {
        assertThat(service.isEligibleForRecommendations(
                "V001", activities, true, false, 1)).isFalse();
    }

    @Test
    void notEligible_whenActivitiesNull() {
        assertThat(service.isEligibleForRecommendations(
                "V001", null, true, true, 1)).isFalse();
    }

    @Test
    void notEligible_whenBelowMinimumActivityCount() {
        assertThat(service.isEligibleForRecommendations(
                "V001", activities, true, true, 5)).isFalse();
    }

    @Test
    void notEligible_whenNoActivityCountsTowardRecommendations() {
        WatchlistEntry unwatched = new WatchlistEntry("A002","V001","Dune","SciFi");
        assertThat(service.isEligibleForRecommendations(
                "V001", List.of(unwatched), true, true, 1)).isFalse();
    }

    // ── generateFromSession ────────────────────────────────────

    @Test
    void generate_returnsSuccess_whenSessionValidAndCatalogueHasMatch() {
        StreamSession s = new StreamSession("A001","V001","The Bear",45,"Drama");
        RecommendationResult result = service.generateFromSession(s, catalogue);
        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.recommendedTitles()).isNotEmpty();
    }

    @Test
    void generate_excludesCurrentlyWatchedTitle() {
        StreamSession s = new StreamSession("A001","V001","The Bear",45,"Drama");
        RecommendationResult result = service.generateFromSession(s, catalogue);
        assertThat(result.recommendedTitles()).doesNotContain("The Bear");
    }

    @Test
    void generate_failsWhenSessionIsNull() {
        RecommendationResult result = service.generateFromSession(null, catalogue);
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.issues()).contains("Session is null");
    }

    @Test
    void generate_failsWhenSessionHasNoTitle() {
        StreamSession s = new StreamSession("A001","V001","",45,"Drama");
        RecommendationResult result = service.generateFromSession(s, catalogue);
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.issues()).contains("Session has no title");
    }

    @Test
    void generate_failsWhenSessionTooShort() {
        StreamSession s = new StreamSession("A001","V001","The Bear",2,"Drama");
        RecommendationResult result = service.generateFromSession(s, catalogue);
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.issues()).contains("Session too short to generate recommendations");
    }

    @Test
    void generate_failsWhenCatalogueIsNull() {
        StreamSession s = new StreamSession("A001","V001","The Bear",45,"Drama");
        RecommendationResult result = service.generateFromSession(s, null);
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.issues()).contains("Catalogue is empty or null");
    }

    @Test
    void generate_failsWhenNoGenreMatchInCatalogue() {
        StreamSession s = new StreamSession("A001","V001","Dune",120,"SciFi");
        RecommendationResult result = service.generateFromSession(s, List.of("The Bear","Succession"));
        assertThat(result.isSuccessful()).isFalse();
        assertThat(result.issues().get(0)).contains("No catalogue titles match genre");
    }
}

