package com.phone.services

import com.phone.models.{CallPrice, CustomerCall}
import com.phone.promotions.Promotion

class PromotionService {

  def applyPromotions(calls: Map[CustomerCall, CallPrice], promotions: Promotion*): Map[CustomerCall, CallPrice] =
    calls.filter(e => promotions.forall(p => p.applyPromotion(Map(e._1 -> e._2)).nonEmpty))
}
