package ex2

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Ex2Test:
  import Question.*

  var conferenceReviewing: ConferenceReviewing = ConferenceReviewing()
  val someScores: Map[Question, Int] = Map(Relevance -> 8, Significance -> 8, Confidence -> 7, Final -> 8)

  @BeforeEach def init(): Unit =
    conferenceReviewing = ConferenceReviewing()
    conferenceReviewing.loadReview(1, 8, 8, 6, 8); // 4.8 è il voto finale pesato (usato da averageWeightedFinalScoreMap)
    conferenceReviewing.loadReview(1, 9, 9, 6, 9); // 5.4
    conferenceReviewing.loadReview(2, 9, 9, 10, 9); // 9.0
    conferenceReviewing.loadReview(2, 4, 6, 10, 6); // 6.0
    conferenceReviewing.loadReview(3, 3, 3, 3, 3); // 0.9
    conferenceReviewing.loadReview(3, 4, 4, 4, 4); // 1.6
    conferenceReviewing.loadReview(4, 6, 6, 6, 6); // 3.6
    conferenceReviewing.loadReview(4, 7, 7, 8, 7); // 5.6
    conferenceReviewing.loadReview(4, someScores); // 5.6 ?
    conferenceReviewing.loadReview(5, 6, 6, 6, 10); // 6.0
    conferenceReviewing.loadReview(5, 7, 7, 7, 10); // 7.0

  @Test def testOrderedScores(): Unit =
    assertEquals(conferenceReviewing.orderedScores(2, Relevance), List(4, 9))
    assertEquals(conferenceReviewing.orderedScores(4, Confidence), List(6, 7, 8))
    assertEquals(conferenceReviewing.orderedScores(5, Final), List(10, 10))

  @Test def testAverageFinalScore(): Unit =
    assertEquals(8.5, conferenceReviewing.averageFinalScore(1), 0.01)
    assertEquals(7.5, conferenceReviewing.averageFinalScore(2), 0.01)
    assertEquals(3.5, conferenceReviewing.averageFinalScore(3), 0.01)
    assertEquals(7.0, conferenceReviewing.averageFinalScore(4), 0.01)
    assertEquals(10.0, conferenceReviewing.averageFinalScore(5), 0.01)
    conferenceReviewing.loadReview(10, 8, 8, 6, 8)
    assertEquals(8.0, conferenceReviewing.averageFinalScore(10), 0.01)
    conferenceReviewing.loadReview(10, 9, 9, 6, 9)
    assertEquals(8.5, conferenceReviewing.averageFinalScore(10), 0.01)
    conferenceReviewing.loadReview(10, someScores)
    assertEquals(8.33, conferenceReviewing.averageFinalScore(10), 0.01)

  @Test def testAcceptedArticles(): Unit =
    assertEquals(Set(1, 2, 4), conferenceReviewing.acceptedArticles)