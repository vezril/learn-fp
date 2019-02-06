package learnfp.typeclass

trait TotalOrder[A] {
  def less(lhs: A, rhs: A): Boolean
}

object TotalOrderInstances {

  implicit val intInstance: TotalOrder[Int] = (lhs, rhs) => lhs < rhs
  implicit val stringInstance: TotalOrder[String] = (lhs, rhs) => lhs.toInt < rhs.toInt

  implicit def listInstance[T](implicit suborder: TotalOrder[T]): TotalOrder[List[T]] =
    (lhs, rhs) => lhs.zip(rhs).forall({case (l, r) => suborder.less(l, r)})
}

object Comparator {
  @annotation.implicitNotFound("No instance of TotalOrder found")
  def less[A](lhs: A, rhs: A)(implicit order: TotalOrder[A]) = {
    order.less(lhs, rhs)
  }
}