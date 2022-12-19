package fr.esgi.mower

import domain.Input.Input

import fr.esgi.mower.domain.Instruction.Instruction

package object engine {
  trait Engine[I, O] {
    def run(input: I): Unit
    def get(): O
  }
  
  trait Computer[I, O] {
    def compute(input: I): O
  }
}
