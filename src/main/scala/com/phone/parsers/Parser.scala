package com.phone.parsers

import scala.io.Source

trait Parser[A] {
  def readLines(reader: Source): List[A]
}
