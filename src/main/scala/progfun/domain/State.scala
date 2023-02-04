package progfun.domain

case class State(position: (Int, Int), orientation: Orientation.Orientation) {
  def setPosition(position: (Int, Int)): State = {
    this.copy(position = position)
  }

  def setOrientation(orientation: Orientation.Orientation): State = {
    this.copy(orientation = orientation)
  }
}
