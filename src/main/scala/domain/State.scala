package fr.esgi.mower
package domain

case class State(var position: (Int, Int), var orientation: Orientation.Orientation) {
  def setPosition(position: (Int, Int)): Unit = {
    this.position = position
  }

  def setOrientation(orientation: Orientation.Orientation): Unit = {
    this.orientation = orientation
  }
}
