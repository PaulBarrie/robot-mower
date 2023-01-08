package fr.esgi.mower
package io

import java.io.PrintWriter

case class File(final val path: String, final val content: String) extends io[File] {
  override def write(content: String): Unit = {
    new PrintWriter(path) {
      write(content); close()
    }
  }

  override def get(): String = content

  override def toList(): List[String] = content.split("\\n").map(_.trim).toList
}
