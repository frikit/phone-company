package com.phone.parser

import scala.io.Source

trait Parser[A] {
  def readLines(reader: Source): List[A]
}
