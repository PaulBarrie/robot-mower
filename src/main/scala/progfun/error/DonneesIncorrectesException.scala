package progfun.error

final case class DonneesIncorrectesException(message: String)
    extends Exception(message)
