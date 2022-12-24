package fr.esgi.mower

package object io {
  trait io[O] {
    def read(): O

    def write(content: String): Unit

    def get(): String

    def toList(): List[String]
  }
}
