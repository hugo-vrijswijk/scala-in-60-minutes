//> using lib "org.scalameta::munit:1.0.0-M1"

package demo

import demo.*

class TestSuite extends munit.FunSuite {
  test("hello") {
    val expected = Seq("main.scala", "files.test.scala")
    val obtained = filesByExtension("scala").map(_.last)
    assertEquals(obtained, expected)
  }
}
