package ex2

import org.junit.*
import org.junit.Assert.*

class Ex2Test:

  val conferenceReviewing: ConferenceReviewing = ConferenceReviewing()
  val someScores: Map[Question, Int] =
    Map(Question.Relevance -> 8, Question.Significance -> 8, Question.Confidence -> 7, Question.Final -> 8)

  @Test def getCorrectAverageFinalScores(): Unit =
    conferenceReviewing.loadReview(1, 8, 8, 6, 8)
    assertEquals(8.0, conferenceReviewing.averageFinalScore(1), 0.01)
    conferenceReviewing.loadReview(1, 9, 9, 6, 9)
    assertEquals(8.5, conferenceReviewing.averageFinalScore(1), 0.01)
    conferenceReviewing.loadReview(1, someScores)
    assertEquals(8.33, conferenceReviewing.averageFinalScore(1), 0.01)