package progfun.engine

trait Engine[I, O] {
  def run(input: I): Unit

  def get(): O
}
