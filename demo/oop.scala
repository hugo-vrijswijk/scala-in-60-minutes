trait Pet

class Dog(name: String) extends Pet {
  def call(): String = s"Come here, ${name}!"
}

class Cat extends Pet {
  def call(): String = s"Meow!"

  private def meow(): String = s"Sneaky meow!"
}
