package com.phone.services

import com.phone.BaseSpec
import com.phone.models.{CallDuration, CallPrice, CustomerCall, PhoneNumber}
import com.phone.promotions.Promotion

import java.time.Duration

class PromotionServiceSpec extends BaseSpec {

  val promotionService = new PromotionService()

  val noPromotion: Promotion = (callsWithPrices: Map[CustomerCall, CallPrice]) => callsWithPrices
  val promotionForCustomerA: Promotion = (callsWithPrices: Map[CustomerCall, CallPrice]) => callsWithPrices.filter(c => c._1.customerID != "A")
  val promotionForCustomerB: Promotion = (callsWithPrices: Map[CustomerCall, CallPrice]) => callsWithPrices.filter(c => c._1.customerID != "B")

  val callsHistory = Map(
    //A
    (CustomerCall("A", PhoneNumber("123"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    (CustomerCall("A", PhoneNumber("456"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    //B
    (CustomerCall("B", PhoneNumber("123"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    (CustomerCall("B", PhoneNumber("456"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    (CustomerCall("B", PhoneNumber("789"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05))
  )

  "Apply promotion on call history" should "return same list when no promotions provided" in {
    promotionService.applyPromotions(callsHistory, noPromotion) should be(callsHistory)
  }

  it should "filter calls by one promotion" in {
    promotionService.applyPromotions(callsHistory, promotionForCustomerA).size should be(3) //only B remain
  }

  it should "filter calls by one promotion2" in {
    promotionService.applyPromotions(callsHistory, promotionForCustomerB).size should be(2) //only A remain
  }

  it should "filter calls by two promotions" in {
    promotionService.applyPromotions(callsHistory, promotionForCustomerA, promotionForCustomerB).size should be(0) //no customers remain
  }
}
