package twod.particles

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class RainbowParticle(pos: Vec2, vel: Vec2, size: Int, life: Int) extends Particle(pos, vel, size, life) {
  
  protected val r = util.Random.nextDouble
  protected val g = util.Random.nextDouble
  protected val b = util.Random.nextDouble
  
  def display(g: GraphicsContext) {
    g.fill = Color.color(r, this.g, b, getLifetimeRatio)
    g.fillOval(pos.x - (size / 2), pos.y - (size / 2), size, size)
  }
  
  def isOffscreen(): Boolean = {
    if(pos.x > 800 || pos.y > 600 || pos.x < 0) {
      return true
    }
    
    return false
  }

}