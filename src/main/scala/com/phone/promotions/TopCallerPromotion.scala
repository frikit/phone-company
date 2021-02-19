package com.phone.promotions

import com.phone.models.{CallPrice, CustomerCall}

class TopCallerPromotion extends Promotion {

  def applyPromotion(callsWithPrices: Map[CustomerCall, CallPrice]): Map[CustomerCall, CallPrice] = {
    val prices = callsWithPrices.groupMap(c => c._1.customerID)(identity)
      .map{ c =>
        val totalPrice = c._2.map(e => e._2.callCost).sum
        (c._1, totalPrice)
      }
      .toList
      .sortBy(e => e._2)

    if (prices.isEmpty) {
      Map()
    } else {
      val topCaller = prices.last._1

      callsWithPrices.filter(c => c._1.customerID != topCaller)
    }
  }

}
