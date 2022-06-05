//> using lib "org.scalameta::munit:1.0.0-M1"

package demo

import demo.*

class TestSuite extends munit.FunSuite {
  test("hello") {
    assertEquals("hello", "hello")
  }
}
