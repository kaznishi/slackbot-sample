package process

object SlackWatchProcess extends BaseProcess with App {
  startProcess {
    // ざっくりイメージこんな処理の流れ。各class定義の実装はまだ
    //    val chatMonitor = slackChatMonitor
    //    val actionDecider = YatoBotActionDecider
    //    val actionRunner = YatoBotActionRunner
    //    val responser = slackResponser
    //
    //    chatMonitor.onMessage { message =>
    //      for (
    //        actionF <- actionDecider.decidefromMessage(message)
    //        responseF <- actionRunner.run(actionF)
    //        _ <- responser.response(responseF)
    //      ) yield ()
    //    }
  }
}