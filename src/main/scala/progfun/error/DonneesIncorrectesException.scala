package progfun.error

final case class DonneesIncorrectesException(message: String,
                                             private val cause: Throwable = None.orNull)
  extends Exception(message, cause)