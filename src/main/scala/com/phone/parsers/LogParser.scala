package com.phone.parsers

import com.phone.models.{CallDuration, CustomerCall, PhoneNumber}

import scala.io.Source

class LogParser extends Parser[CustomerCall] {

  val requiredNumberOfFields = 3

  override def readLines(reader: Source): List[CustomerCall] = {
    reader.getLines().toList
      .filter(!_.isBlank)
      .map { line =>
      val cols = line.split(" ").map(_.trim)

      if (cols.length != requiredNumberOfFields) {
        throw new RuntimeException(s"Line should contains $requiredNumberOfFields fields! Your line is $line")
      }

      val customerID = cols(0)
      val phoneNumber = cols(1)
      val duration = cols(2)

      CustomerCall(
        customerID,
        PhoneNumber(phoneNumber),
        CallDuration(duration)
      )
    }
  }
}
