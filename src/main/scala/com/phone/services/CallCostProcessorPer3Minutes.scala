package com.phone.services
import com.phone.models.{CallPrice, CustomerCall}

import java.time.Duration

class CallCostProcessorPer3Minutes extends CallCostProcessor {

  private lazy val threeMinuteDuration = Duration.ofMinutes(3)
  private lazy val firstMinutesRate = 0.05
  private lazy val extraMinutesRate = 0.03

  private def calculateCost(call: CustomerCall): CallPrice = {
    if (call.duration.duration.toMinutes > 3) {
      //calculate first 3 minutes
      val threeMinutesCost = threeMinuteDuration.toSeconds * firstMinutesRate

      //calculate remain
      val extraCost = call.duration.duration.minus(threeMinuteDuration).toSeconds * extraMinutesRate

      val finalPrice = threeMinutesCost + extraCost
      CallPrice(finalPrice)
    } else {
      CallPrice(call.duration.duration.toSeconds * firstMinutesRate)
    }
  }

  override def calculateCost(calls: List[CustomerCall]): Map[CustomerCall, CallPrice] = {
    val prices =  calls.map(c => calculateCost(c))

    calls.zip(prices).toMap
  }
}
