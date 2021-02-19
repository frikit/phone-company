package com.phone.promotions

import com.phone.models.{CallPrice, CustomerCall}

trait Promotion {
  def applyPromotion(callsWithPrices: Map[CustomerCall, CallPrice]): Map[CustomerCall, CallPrice]
}
