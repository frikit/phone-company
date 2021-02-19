package com.phone.services

import com.phone.models.{CallPrice, CustomerCall}

trait CallCostProcessor {
  def calculateCost(calls: List[CustomerCall]): Map[CustomerCall, CallPrice]
}
