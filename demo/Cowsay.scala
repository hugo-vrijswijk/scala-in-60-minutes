object Cowsay {

  def text(message: String): String = {
    val len       = message.length
    val topBar    = "_" * (len + 2)
    val bottomBar = "-" * (len + 2)
    s""" $topBar
       |< $message >
       | $bottomBar""".stripMargin +
      """
        |        \   ^__^
        |         \  (oo)\_______
        |            (__)\       )\/\
        |                ||----w |
        |                ||     ||
        |""".stripMargin
  }

  def main(args: Array[String]): Unit = {
    val message = args match {
      case Array(msg) => msg
      case _          => "Hello, world!"
    }

    println(text(message))
  }
}
// scala-cli package demo/Cowsay.scala -o cowsay.js --js
// scala-cli package demo/Cowsay.scala -o cowsay --native
