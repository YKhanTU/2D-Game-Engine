package twod

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext

abstract class GameObject(protected var pos: Vec2) {
  
  def display(g: GraphicsContext)
  def timeStep(): Unit
}