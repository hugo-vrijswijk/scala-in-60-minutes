//> using lib "org.scalameta::munit::1.0.0-M1"

class CowsayTest extends munit.FunSuite {

  test("hello") {
    val content = Cowsay.text("hello")
    val expected = """ _______
                     |< hello >
                     | -------
                     |        \   ^__^
                     |         \  (oo)\_______
                     |            (__)\       )\/\
                     |                ||----w |
                     |                ||     ||
                     |""".stripMargin

    assertEquals(content, expected)
  }
}
