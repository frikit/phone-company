package com.phone.models

import com.phone.BaseSpec

class CallDurationSpec extends BaseSpec {

  lazy val goodFormats = List(
    ("00:00:00", 0), // 0 seconds
    ("00:00:01", 1), // 1 second
    ("00:01:00", 60), // 1 minute
    ("01:00:00", 3600), // 1 hour
    ("01:01:01", 3661) // 1 hour, 1 minute, 1 second
  )
  lazy val badFormats = List("00:00", "00:00-00")

  "A Call duration" should "parse correct string format from log" in {
    CallDuration("00:00:00").duration.toSeconds should be(0)
  }

  goodFormats.foreach { tuple =>
    val format = tuple._1
    val expectedSeconds = tuple._2
    it should s"parse valid format($format)" in {
      val current = CallDuration(format).duration.toSeconds
      current should be(expectedSeconds)
    }
  }

  badFormats.foreach { format =>
    it should s"throw exception when incorrect format($format)" in {
      lazy val expectedMessage = s"We only support format 01:02:03 as hh:mm:ss but you pass $format"

      val exception = intercept[RuntimeException] {
        CallDuration(format)
      }

      exception.getMessage should be(expectedMessage)
    }
  }

}
