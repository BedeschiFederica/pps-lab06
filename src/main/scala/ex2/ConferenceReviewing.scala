package ex2

enum Question:
  case Relevance, Significance, Confidence, Final

trait ConferenceReviewing:
  def loadReview(article: Int, scores: Map[Question, Int]): Unit
  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit
  def orderedScores(article: Int, question: Question): Seq[Int]
  def averageFinalScore(article: Int): Double
  def acceptedArticles: Set[Int]
  def sortedAcceptedArticles: Seq[(Int, Double)]
  def averageWeightedFinalScoreMap: Map[Int, Double]

object ConferenceReviewing:
  def apply(): ConferenceReviewing = ConferenceReviewingImpl(Seq())

  private class ConferenceReviewingImpl(private var reviews: Seq[(Int, Map[Question, Int])]) extends ConferenceReviewing:
    import Question.*

    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      require(scores.size == Question.values.length)
      reviews = reviews :+ (article, scores)

    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
      reviews = reviews :+ (article, Map(Relevance -> relevance, Significance -> significance,
        Confidence -> confidence, Final -> fin))

    override def orderedScores(article: Int, question: Question): Seq[Int] =
      reviews.filter(_._1 == article).map(_._2(question)).sorted

    override def averageFinalScore(article: Int): Double =
      reviews.filter(_._1 == article).map(_._2(Final)).sum.toDouble / reviews.count(_._1 == article)

    override def acceptedArticles: Set[Int] =
      reviews.map(_._1).distinct
        .filter(a => averageFinalScore(a) > 5.0 && reviews.filter(_._1 == a).map(_._2(Relevance)).exists(_ >= 8.0))
        .toSet

    override def sortedAcceptedArticles: Seq[(Int, Double)] = ???
    override def averageWeightedFinalScoreMap: Map[Int, Double] = ???