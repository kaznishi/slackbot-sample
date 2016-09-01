package job.process

object SlackWatchProcess extends BaseProcess with App {
  startProcess {
// ざっくりイメージこんな処理の流れ。各class定義の実装はまだ
//    val chatMonitor = slackChatMonitor
//    val actionExtractor = YatoBotActionExtractor
//    val actionRunner = YatoBotActionRunner
//    val responder = slackResponder
//
//    chatMonitor.onMessage { message =>
//      for (
//        actionF <- actionDecider.decidefromMessage(message)
//        responseF <- actionRunner.run(actionF)
//        _ <- responder.response(responseF)
//      ) yield ()
//    }
  }
}