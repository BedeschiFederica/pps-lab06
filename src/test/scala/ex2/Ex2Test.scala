package ex2

import org.junit.*
import org.junit.Assert.*

class Ex2Test:
  import Question.*

  val conferenceReviewing: ConferenceReviewing = ConferenceReviewing()
  val someScores: Map[Question, Int] = Map(Relevance -> 8, Significance -> 8, Confidence -> 7, Final -> 8)

  @Test def testAverageFinalScore(): Unit =
    conferenceReviewing.loadReview(1, 8, 8, 6, 8)
    assertEquals(8.0, conferenceReviewing.averageFinalScore(1), 0.01)
    conferenceReviewing.loadReview(1, 9, 9, 6, 9)
    assertEquals(8.5, conferenceReviewing.averageFinalScore(1), 0.01)
    conferenceReviewing.loadReview(1, someScores)
    assertEquals(8.33, conferenceReviewing.averageFinalScore(1), 0.01)