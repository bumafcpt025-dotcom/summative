package za.co.wethinkcode.streaming;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.streaming.model.StreamSession;

import static org.assertj.core.api.Assertions.assertThat;

class StreamSessionTest {

    private StreamSession make(int durationMinutes) {
        return new StreamSession("A001","V001","Succession",durationMinutes,"Drama");
    }

    @Test
    void constructor_setsAllFields() {
        StreamSession s = new StreamSession("A001","V001","Succession",52,"Drama");
        assertThat(s.activityId()).isEqualTo("A001");
        assertThat(s.viewerId()).isEqualTo("V001");
        assertThat(s.title()).isEqualTo("Succession");
        assertThat(s.durationMinutes()).isEqualTo(52);
        assertThat(s.genre()).isEqualTo("Drama");
    }

    @Test
    void activityType_returnsStreamSession() {
        assertThat(make(30).activityType()).isEqualTo("STREAM_SESSION");
    }

    @Test
    void isCompletedView_trueWhenAboveThreshold() {
        assertThat(make(30).isCompletedView()).isTrue();
    }

    @Test
    void isCompletedView_falseWhenBelowThreshold() {
        assertThat(make(2).isCompletedView()).isFalse();
    }

    @Test
    void isCompletedView_falseAtExactlyThreshold() {
        assertThat(make(StreamSession.SHORT_SESSION_MINUTES).isCompletedView()).isFalse();
    }

    @Test
    void countsTowardRecommendations_trueForCompletedView() {
        assertThat(make(30).countsTowardRecommendations()).isTrue();
    }

    @Test
    void countsTowardRecommendations_falseForShortSession() {
        assertThat(make(2).countsTowardRecommendations()).isFalse();
    }
}
