package services.yatobot

import entities.BotAction
import services.ActionRunner

class YatoBotActionRunner extends ActionRunner {
  def run(optAction: Option[BotAction]) = {
    println("action run")
    val service = new ActionRunService
    service.run(optAction)
  }
}
