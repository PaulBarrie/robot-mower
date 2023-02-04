package progfun.parser

import progfun.error.DonneesIncorrectesException

trait Parser[I, T] {
  def marshall(input: I): Either[DonneesIncorrectesException, T]

  def unmarshall(input: T): String
}
