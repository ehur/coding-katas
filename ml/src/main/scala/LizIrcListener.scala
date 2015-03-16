import org.schwering.irc.lib.{IRCModeParser, IRCUser, IRCEventListener}

class LizIrcListener extends IRCEventListener {
  def onRegistered {
    println("Connected")
  }

  def onDisconnected {
    println("Disconnected")
  }

  def onError(msg: String) {
    println("Error: " + msg)
  }

  def onError(num: Int, msg: String) {
    println("Error #" + num + ": " + msg)
  }

  def onInvite(chan: String, u: IRCUser, nickPass: String) {
    println(chan + "> " + u.getNick + " invites " + nickPass)
  }

  def onJoin(chan: String, u: IRCUser) {
    println(chan + "> " + u.getNick + " joins")
  }

  def onKick(chan: String, u: IRCUser, nickPass: String, msg: String) {
    println(chan + "> " + u.getNick + " kicks " + nickPass)
  }

  def onMode(u: IRCUser, nickPass: String, mode: String) {
    println("Mode: " + u.getNick + " sets modes " + mode + " " + nickPass)
  }

  def onMode(chan: String, u: IRCUser, mp: IRCModeParser) {
    println(chan + "> " + u.getNick + " sets mode: " + mp.getLine)
  }

  def onNick(u: IRCUser, nickNew: String) {
    println("Nick: " + u.getNick + " is now known as " + nickNew)
  }

  def onNotice(target: String, u: IRCUser, msg: String) {
    println(target + "> " + u.getNick + " (notice): " + msg)
  }

  def onPart(chan: String, u: IRCUser, msg: String) {
    println(chan + "> " + u.getNick + " parts")
  }

  def onPrivmsg(chan: String, u: IRCUser, msg: String) {
    println(chan + "> " + u.getNick + ": " + msg)
  }

  def onQuit(u: IRCUser, msg: String) {
    println("Quit: " + u.getNick)
  }

  def onReply(num: Int, value: String, msg: String) {
    println("Reply #" + num + ": " + value + " " + msg)
  }

  def onTopic(chan: String, u: IRCUser, topic: String) {
    println(chan + "> " + u.getNick + " changes topic into: " + topic)
  }

  def onPing(p: String) {
  }

  def unknown(a: String, b: String, c: String, d: String) {
   println("UNKNOWN: " + a + " " + b + " " + c + " " + d)
  }
}
