package fr.esgi.mower

import io.File

import org.scalatest.flatspec._
import org.scalatest.matchers._

import java.io.FileNotFoundException

class FileTest extends AnyFlatSpec with should.Matchers {

  "File content" should "be empty at first" in {
    val file = File("src/test/resources/input.txt")
    file.get() should be("")
  }

  "File content" should "be read in list format" in {
    val file = File("src/test/resources/input.txt").read()
    file.toList() should be(List("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"))
  }

  it should "throw FileNotFoundException if file does not exist" in {
    val file = File("unknown/path")
    a[FileNotFoundException] should be thrownBy {
      file.read()
    }
  }
}