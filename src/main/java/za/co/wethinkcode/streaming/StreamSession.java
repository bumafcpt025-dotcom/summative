package za.co.wethinkcode.streaming.model;

/**
 * TODO Q4.2: This class does not compile.
 * It must extend ViewerActivity and call super() correctly in the constructor.
 * Do not change any other logic.
 */
public class StreamSession {   // <-- FIX THIS LINE

    public static final int SHORT_SESSION_MINUTES = 5;

    private final String title;
    private final int    durationMinutes;
    private final String genre;

    // TODO Q4.2: fix the constructor — call super(activityId, viewerId) as first line
    public StreamSession(String activityId, String viewerId,
                         String title, int durationMinutes, String genre) {
        // super(...) call missing
        this.title           = title;
        this.durationMinutes = durationMinutes;
        this.genre           = genre;
    }

    public String title()           { return title;           }
    public int    durationMinutes() { return durationMinutes; }
    public String genre()           { return genre;           }

    public boolean isCompletedView() {
        return durationMinutes > SHORT_SESSION_MINUTES;
    }

    @Override
    public String activityType() { return "STREAM_SESSION"; }

    @Override
    public boolean countsTowardRecommendations() {
        return isCompletedView();
    }
}

