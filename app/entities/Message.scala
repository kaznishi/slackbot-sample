package entities
import slack.models.{Message => SlackClientMessage}

case class Message(roomId: String, fromUserId: String, body: String)
object Message {
  def apply(a: Any): Message = {
    a match {
      case x: SlackClientMessage => new Message(x.channel, x.user, x.text)
      case _ => new Message("", "", "")
    }
  }
}