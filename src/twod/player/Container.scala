package twod.player

trait Container {
  
  def add(i: Item)
  def add(i: Item, index: Int)
  def get(index: Int)
  def remove()
  def remove(index: Int)
  def size(): Int
}