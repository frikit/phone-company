package com.phone.services

import com.phone.models.{CallPrice, CustomerCall}
import com.phone.promotions.Promotion

import scala.annotation.tailrec

class PromotionService {

  @tailrec
  private def applyPromotion(
                              calls: Map[CustomerCall, CallPrice],
                              promotion: Promotion,
                              promotions: Seq[Promotion]
                            ): Map[CustomerCall, CallPrice] = {
    if (promotions.isEmpty) {
      promotion.applyPromotion(calls)
    } else {
      applyPromotion(promotion.applyPromotion(calls), promotions.head, promotions.tail)
    }
  }

  def applyPromotions(calls: Map[CustomerCall, CallPrice], promotions: Promotion*): Map[CustomerCall, CallPrice] = {
    applyPromotion(calls, promotions.head, promotions.tail)
  }
}
