package fr.esgi.mower
package kernel.error

final case class DonneesIncorrectesException(private val message: String = "",
                                             private val cause: Throwable = None.orNull)
  extends Exception(message, cause)