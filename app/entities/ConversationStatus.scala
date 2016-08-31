package entities

case class ConversationStatus(userId: Option[String], roomId: Option[String], scenarioId: Int, scenarioOption: Option[ScenarioOption])


