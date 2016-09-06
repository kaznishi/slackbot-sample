package services.yatobot

import entities.{BotAction, Message}

class ActionDecideService {

  def run(message: Message): Option[BotAction] = {
    message.body match {
      case "hi" => Some(YatoBotScenario1hiAction)
      case "hey" => Some(YatoBotScenario1heyAction)
      case _ => None
    }
  }

}

trait YatoBotAction extends BotAction

object YatoBotScenario1hiAction extends YatoBotAction
object YatoBotScenario1heyAction extends YatoBotAction
