package com.phone

import com.phone.parsers.LogParser
import com.phone.promotions.TopCallerPromotion
import com.phone.services.{CallCostProcessorPer3Minutes, PromotionService}

object Main extends App {

  val file = io.Source.fromFile("/Users/frikit/IdeaProjects/phone-company2/src/main/resources/calls.log")
  val logParser = new LogParser()

  val calls = logParser.readLines(file)
  file.close()

  //calculate cost
  val costCalculator = new CallCostProcessorPer3Minutes()
  val callsWithPrices = costCalculator.calculateCost(calls)

  //apply promotions
  val promotionService = new PromotionService()

  val topCallerPromotion = new TopCallerPromotion()
  val applyPromotions = promotionService.applyPromotions(callsWithPrices, topCallerPromotion)

  applyPromotions.foreach(println(_))

}


