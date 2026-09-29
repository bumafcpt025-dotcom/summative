package za.co.wethinkcode.streaming.model;

/**
 * Abstract base class for all viewer activity types on the StreamWise platform.
 * StreamSession and WatchlistEntry must both extend this class.
 */
public abstract class ViewerActivity {

    private final String activityId;
    private final String viewerId;

    protected ViewerActivity(String activityId, String viewerId) {
        this.activityId = activityId;
        this.viewerId   = viewerId;
    }

    public String activityId() { return activityId; }
    public String viewerId()   { return viewerId;   }

    /** Returns a short label describing the type of activity. */
    public abstract String activityType();

    /** Returns true if this activity should count toward recommendation scoring. */
    public abstract boolean countsTowardRecommendations();

    @Override
    public String toString() {
        return activityType() + "[id=" + activityId + ", viewer=" + viewerId + "]";
    }
}


