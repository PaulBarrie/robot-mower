import org.scalatest.flatspec._
import org.scalatest.matchers._
import progfun.io.{File, FileReader}

import java.io.FileNotFoundException

class FileTest extends AnyFlatSpec with should.Matchers {

  "File content" should "be read in list format" in {
    val fileReader = FileReader("src/test/resources/input.txt")
    val file = File(fileReader.path, fileReader.read())
    file.toList() should be(
      List("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA")
    )
  }

  it should "throw FileNotFoundException if file does not exist" in {
    val fileReader = FileReader("unknown/path")
    a[FileNotFoundException] should be thrownBy {
      fileReader.read()
    }
  }
}
