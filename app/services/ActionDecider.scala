package services

import entities.{BotAction, Message}

trait ActionDecider {
  def decideFromMessage(message: Message): Option[BotAction]
}