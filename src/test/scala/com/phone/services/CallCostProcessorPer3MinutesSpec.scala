package com.phone.services

import com.phone.BaseSpec
import com.phone.models.{CallDuration, CallPrice, CustomerCall, PhoneNumber}

import java.time.Duration

class CallCostProcessorPer3MinutesSpec extends BaseSpec {

  val calculator = new CallCostProcessorPer3Minutes()

  "Call Calculator 3 minute logic" should "return empty list for empty input" in {
    calculator.calculateCost(Nil).size should be(0)
  }

  it should "return correct price for call under 3 minutes" in {
    val minutes = 1
    val duration = Duration.ofMinutes(minutes)
    val input = List(CustomerCall(customerID = "A", phoneNumber = PhoneNumber("123"), duration = new CallDuration(duration)))

    val current = calculator.calculateCost(input)
    val expected = CallPrice(minutes * 3.0)

    current.get(input.head).get should be(expected)
  }

  it should "return correct price for call exact 3 minutes" in {
    val minutes = 3
    val duration = Duration.ofMinutes(minutes)
    val input = List(CustomerCall(customerID = "A", phoneNumber = PhoneNumber("123"), duration = new CallDuration(duration)))

    val current = calculator.calculateCost(input)
    val expected = CallPrice(minutes * 3.0)

    current.get(input.head).get should be(expected)
  }


  it should "return correct price for call exact 4 minutes(3 standard rate and 1 minute as extra)" in {
    val firstMinutes = 3
    val extraMinutes = 1
    val duration = Duration.ofMinutes(firstMinutes + extraMinutes)
    val input = List(CustomerCall(customerID = "A", phoneNumber = PhoneNumber("123"), duration = new CallDuration(duration)))

    val current = calculator.calculateCost(input)
    val expected = CallPrice((firstMinutes * 3.0) + (extraMinutes * 1.8))

    current.get(input.head).get should be(expected)
  }

}
