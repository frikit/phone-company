package com.phone.models

import java.time.Duration

case class CallDuration(
                         val duration: Duration
                       )

object CallDuration {

  def buildTimePattern(hours: String, minutes: String, seconds: String): String =
    s"PT${hours}H${minutes}M${seconds}S"

  private def isValidTime(values: Array[String]): Boolean =
    values.length == 3 &&
      values.forall(_.length == 2)

  def apply(duration: String): CallDuration = {
    val values = duration.split(":").map(_.trim)

    if (!isValidTime(values)) {
      throw new RuntimeException(s"We only support format 01:02:03 as hh:mm:ss but you pass $duration")
    }

    val hours = values(0)
    val minutes = values(1)
    val seconds = values(2)

    val durationFormat = buildTimePattern(hours, minutes, seconds)

    new CallDuration(Duration.parse(durationFormat))
  }
}
