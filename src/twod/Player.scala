package twod

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Player(initPos: Vec2) extends GameObject(initPos) {
  
  protected val height = 64
  protected val width = 32
  
  def display(g: GraphicsContext) = {
    g.fill = Color.CRIMSON
    g.fillRect(pos.x, pos.y, width, height)
    g.stroke = Color.BLACK
    g.strokeRect(pos.x + width, pos.y + 15, 45, 10)
    g.fill = Color.BROWN
    g.fillRect(pos.x + width + 1, pos.y + 16, 43, 8)
  }
  
  def timeStep(): Unit = {
    
  }
}