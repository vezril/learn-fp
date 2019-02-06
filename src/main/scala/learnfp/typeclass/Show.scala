package learnfp.typeclass

trait Show[A] {
  def show(x: A):String
}

object Printer {
  def show[A](x :A)(implicit showInstance:Show[A]): String = {
    showInstance.show(x)
  }
}

object ShowInstances {
  implicit val intInstance: Show[Int] = x => s"${x}"

  implicit val doubleInstance: Show[Double] = x => s"${x}"

  implicit def listInstance[T](implicit xShow: Show[T]): Show[List[T]] = xs => xs.map(xShow.show(_)).mkString("[", ", ", "]")
}