package progfun.io

case class FileReader(final val path: String) {
  def read(): String = {
    val source = scala.io.Source.fromFile(path)
    val content = source.mkString
    source.close()
    content
  }
}
