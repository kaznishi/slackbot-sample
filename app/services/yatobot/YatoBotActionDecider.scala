package services.yatobot

import entities.Message
import services.ActionDecider

class YatoBotActionDecider extends ActionDecider {
  def decideFromMessage(message:Message) = {
    println("action decide")

    val service = new ActionDecideService
    service.run(message)
  }
}
