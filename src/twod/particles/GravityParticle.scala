package twod.particles

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color
import scalafx.scene.image.Image

class GravityParticle(pos: Vec2, vel: Vec2, size: Int, life: Int) extends Particle(pos, vel, size, life) {
  
  protected val img = new Image("file:baseparticle.png")
  
  def display(g: GraphicsContext) {
//    g.drawImage(img, pos.x - 5, pos.y - 5, 10, 10)
    g.fill = Color.color(0, 1, getLifetimeRatio, getLifetimeRatio)
    g.fillOval(pos.x - 5, pos.y - 5, 10, 10)
  }
  
  def isOffscreen(): Boolean = {
    if(pos.x > 800 || pos.y > 600 || pos.x < 0) {
      return true
    }
    
    return false
  }
}