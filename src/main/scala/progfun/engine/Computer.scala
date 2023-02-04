package progfun.engine

trait Computer[I, O] {
  def compute(input: I): O
}
