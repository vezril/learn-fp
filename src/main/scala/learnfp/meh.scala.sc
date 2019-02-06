trait Show[A] {
  def show(a: A): String
}

object Show {

  def apply[A](implicit sh: Show[A]): Show[A] = sh

  object ops {
    def show[A: Show](a: A): String = Show[A].show(a)

    implicit class ShowOps[A: Show](a: A) {
      def show = Show[A].show(a)
    }
  }

  implicit val intCanShow: Show[Int] = int => s"int $int"

  implicit val strCanShow: Show[String] = str => s"string $str"
}


println(20.show)
