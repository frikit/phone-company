package com.phone.services

import com.phone.models.{CallPrice, CustomerCall}
import com.phone.promotions.Promotion

class PromotionService {

  def applyPromotions(calls: Map[CustomerCall, CallPrice], promotions: Promotion*): Map[CustomerCall, CallPrice] = {
    var r = calls
    for(p <- promotions) {
      r = p.applyPromotion(r)
    }
    r
  }
}
