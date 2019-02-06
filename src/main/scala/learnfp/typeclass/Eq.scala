package learnfp.typeclass

trait Eq[A] {
  def eq(lhs: A, rhs: A): Boolean
}

object Eq {
  def eq[A](lhs: A, rhs: A)(implicit eqt: Eq[A]): Boolean = eqt.eq(lhs, rhs)
}

class EqOps[A](lhs: A)(implicit eqt: Eq[A]) {
  def ====(rhs: A): Boolean = eqt.eq(lhs, rhs)
}

object EqOps {
  implicit def toEqOps[A](lhs: A)(implicit eqt: Eq[A]) = new EqOps(lhs)
}

object EqInstances {
  implicit val intEqInstance: Eq[Int] = (lhs, rhs) => lhs == rhs

  implicit val stringEqInstance: Eq[String] = (lhs, rhs) => lhs == rhs

  implicit def listEqInstance[A](implicit eqt: Eq[A]): Eq[List[A]] = (lhs, rhs) => (lhs.size == rhs.size) && lhs.zip(rhs).forall({case (l, r) => eqt.eq(l, r)})
}
