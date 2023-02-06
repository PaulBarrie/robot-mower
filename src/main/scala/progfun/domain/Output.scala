package progfun.domain

object Output {
  case class Output(fieldSize: (Int, Int), outputList: List[OutputItem])

  case class OutputItem(
      begin: State,
      instructions: List[Instruction.Instruction],
      end: State
  )
}
