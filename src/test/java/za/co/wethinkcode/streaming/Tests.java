// // ==================== ViewerActivityTest.java ====================
// package za.co.wethinkcode.streaming;

// import org.junit.jupiter.api.Test;
// import za.co.wethinkcode.streaming.model.StreamSession;
// import za.co.wethinkcode.streaming.model.ViewerActivity;
// import za.co.wethinkcode.streaming.model.WatchlistEntry;

// import static org.assertj.core.api.Assertions.assertThat;

// class ViewerActivityTest {

//     @Test
//     void streamSession_isInstanceOfViewerActivity() {
//         StreamSession s = new StreamSession("A001","V001","Dune",120,"SciFi");
//         assertThat(s).isInstanceOf(ViewerActivity.class);
//     }

//     @Test
//     void watchlistEntry_isInstanceOfViewerActivity() {
//         WatchlistEntry e = new WatchlistEntry("A002","V001","Oppenheimer","Drama");
//         assertThat(e).isInstanceOf(ViewerActivity.class);
//     }

//     @Test
//     void activityId_returnedCorrectly() {
//         StreamSession s = new StreamSession("A001","V001","Dune",120,"SciFi");
//         assertThat(s.activityId()).isEqualTo("A001");
//     }

//     @Test
//     void viewerId_returnedCorrectly() {
//         StreamSession s = new StreamSession("A001","V002","Dune",120,"SciFi");
//         assertThat(s.viewerId()).isEqualTo("V002");
//     }
// }


// // ==================== StreamSessionTest.java ====================

// // ==================== WatchlistEntryTest.java ====================

// // ==================== RecommendationServiceTest.java ====================

// // ==================== StreamSchemaTest.java ====================
