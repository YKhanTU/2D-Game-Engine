package twod.particles

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class RandomParticle(pos: Vec2, vel: Vec2, size: Int, life: Int) extends Particle(pos, vel, size, life) {
  
  protected val r = util.Random.nextDouble
  protected val g = util.Random.nextDouble
  protected val b = util.Random.nextDouble
  
  protected var s: Double = size
  
  override def timeStep(): Unit = {
    if(!isDead) {
      pos += vel
      lifeTime -= 1
      
      if(s > 0) {
        s = s - 1
      }
    }
  }
  
  def display(g: GraphicsContext) {
    g.fill = Color.color(r, this.g, b, getLifetimeRatio)
    g.fillOval(pos.x - (s / 2), pos.y - (s / 2), s, s)
  }
  
  def isOffscreen(): Boolean = {
    if(pos.x > 800 || pos.y > 600 || pos.x < 0) {
      return true
    }
    
    return false
  }
  
  override def toString(): String = {
    "(" + pos.x.toInt + ", " + pos.y.toInt + ")"
  }
}