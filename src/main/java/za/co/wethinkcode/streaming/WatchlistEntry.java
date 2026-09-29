package za.co.wethinkcode.streaming.model;

/**
 * TODO Q4.2: This class does not compile.
 * It must extend ViewerActivity and call super() correctly in the constructor.
 * Do not change any other logic.
 */
public class WatchlistEntry {   // <-- FIX THIS LINE

    private final String title;
    private final String genre;
    private boolean      watched = false;

    // TODO Q4.2: fix the constructor — call super(activityId, viewerId) as first line
    public WatchlistEntry(String activityId, String viewerId,
                          String title, String genre) {
        // super(...) call missing
        this.title = title;
        this.genre = genre;
    }

    public String  title()    { return title;   }
    public String  genre()    { return genre;   }
    public boolean isWatched(){ return watched; }

    public void markWatched() {
        if (watched) throw new IllegalStateException("Already marked as watched");
        watched = true;
    }

    @Override
    public String activityType() { return "WATCHLIST_ENTRY"; }

    @Override
    public boolean countsTowardRecommendations() { return watched; }
}

