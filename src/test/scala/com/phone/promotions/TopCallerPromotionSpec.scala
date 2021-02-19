package com.phone.promotions

import com.phone.BaseSpec
import com.phone.models.{CallDuration, CallPrice, CustomerCall, PhoneNumber}

import java.time.Duration

class TopCallerPromotionSpec extends BaseSpec {

  val topCallerPromotions = new TopCallerPromotion()

  val callsHistory = Map(
    //A
    (CustomerCall("A", PhoneNumber("123"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    (CustomerCall("A", PhoneNumber("456"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    //B
    (CustomerCall("B", PhoneNumber("123"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    (CustomerCall("B", PhoneNumber("456"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    (CustomerCall("B", PhoneNumber("789"), CallDuration(Duration.ofSeconds(1))), CallPrice(0.05)),
    //C
    (CustomerCall("C", PhoneNumber("789"), CallDuration(Duration.ofMinutes(1))), CallPrice(1 * 3.0))
  )

  "Top Caller promotion" should "remove top caller from list" in {
    topCallerPromotions.applyPromotion(callsHistory).size should be(callsHistory.size - 1)//remove C from list
  }

  it should "return the same list if list is empty" in {
    topCallerPromotions.applyPromotion(Map()).size should be(0)//none are removed from list
  }
}
