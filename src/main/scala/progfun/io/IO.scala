package progfun.io

trait IO[O] {
  def write(content: String): Unit

  def get(): String

  def toList(): List[String]
}
