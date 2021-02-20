package com.phone.parsers

import com.phone.BaseSpec
import com.phone.models.{CallDuration, CustomerCall, PhoneNumber}

import scala.io.Source

class LogParserSpec extends BaseSpec {
  val parser = new LogParser()

  "Log Parser" should "parse 1 line in log from string stream" in {
    val string = "A 555-333-212 00:00:00"
    val stringStream = Source.fromString(string)

    val expected = List(CustomerCall("A", PhoneNumber("555-333-212"), CallDuration("00:00:00")))

    parser.readLines(stringStream) should be(expected)
  }

  it should "parse 2 lines in log from string stream" in {
    val string = "A 555-333-212 00:00:00\nB 555-333-212 00:00:00"
    val stringStream = Source.fromString(string)

    val expected = List(
      CustomerCall("A", PhoneNumber("555-333-212"), CallDuration("00:00:00")),
      CustomerCall("B", PhoneNumber("555-333-212"), CallDuration("00:00:00"))
    )

    parser.readLines(stringStream) should be(expected)
  }

  it should "parse 0 lines in log from string stream because empty line" in {
    val string = ""
    val stringStream = Source.fromString(string)

    parser.readLines(stringStream).length should be(0)
  }

  it should "parse 0 lines in log from string stream because line with empty spaces" in {
    val string = "   \n   \n   "
    val stringStream = Source.fromString(string)

    parser.readLines(stringStream).length should be(0)
  }

  val invalidRowCombinations = List(
    "A",//1
    "A A",//2
    "A A A A",//4
    "A A A A A",//5
  )

  invalidRowCombinations.foreach{ string =>
    val required = 3
    val input = string.split(" ").length
    val exceptedExceptionError = s"Line should contains $required fields! Your line is $string"

    it should s"throw exception when try to parse a row with $input value($required required)" in {
      val stringStream = Source.fromString(string)

      val exception = intercept[RuntimeException](
        parser.readLines(stringStream)
      )

      exception.getMessage should be(exceptedExceptionError)
    }
  }
}
