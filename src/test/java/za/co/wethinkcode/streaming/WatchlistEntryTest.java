package za.co.wethinkcode.streaming;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.streaming.model.WatchlistEntry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WatchlistEntryTest {

    private WatchlistEntry make() {
        return new WatchlistEntry("A003","V001","The Bear","Drama");
    }

    @Test
    void constructor_setsAllFields() {
        WatchlistEntry e = make();
        assertThat(e.activityId()).isEqualTo("A003");
        assertThat(e.viewerId()).isEqualTo("V001");
        assertThat(e.title()).isEqualTo("The Bear");
        assertThat(e.genre()).isEqualTo("Drama");
    }

    @Test
    void isWatched_defaultsFalse() {
        assertThat(make().isWatched()).isFalse();
    }

    @Test
    void markWatched_setsWatchedTrue() {
        WatchlistEntry e = make();
        e.markWatched();
        assertThat(e.isWatched()).isTrue();
    }

    @Test
    void markWatched_throwsIfAlreadyWatched() {
        WatchlistEntry e = make();
        e.markWatched();
        assertThrows(IllegalStateException.class, e::markWatched);
    }

    @Test
    void countsTowardRecommendations_falseWhenUnwatched() {
        assertThat(make().countsTowardRecommendations()).isFalse();
    }

    @Test
    void countsTowardRecommendations_trueAfterWatched() {
        WatchlistEntry e = make();
        e.markWatched();
        assertThat(e.countsTowardRecommendations()).isTrue();
    }

    @Test
    void activityType_returnsWatchlistEntry() {
        assertThat(make().activityType()).isEqualTo("WATCHLIST_ENTRY");
    }
}

