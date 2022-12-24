package fr.esgi.mower
package parser

import kernel.error.DonneesIncorrectesException

package object parser {
  trait Parser[I, T] {
    def marshall(input: I): Either[DonneesIncorrectesException, T]

    def unmarshall(input: T): String
  }
}
