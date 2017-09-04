package twod.particles

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color
import scala.util.Random

abstract class Particle(var pos: Vec2, protected var vel: Vec2, protected var size: Int, protected val life: Int) {
  
  protected var lifeTime = (life - 0)
  
  def this(pos: Vec2, vel: Vec2) = {
    this(pos, new Vec2(0, 0), 1, (10 + Random.nextInt(5)))
  }
  
  /**
   * Called every 'step' in time.
   */
  def timeStep(): Unit = {
    if(!isDead) {
      pos += vel
      lifeTime -= 1
    }
  }
  
  /**
   * 
   */
  def display(g: GraphicsContext)
  
  /**
   * Applies an acceleration force to the particle.
   */
  final def applyAcceleration(acc: Vec2) = {
    vel += acc
  }
  
  def isDead(): Boolean = {
    return lifeTime == 0
  }
  
  def isOffscreen(): Boolean
  
  def shouldRemove(): Boolean = {
    return isOffscreen || isDead
  }
  
  final def isStopped(): Boolean = {
    return vel.x == 0 && vel.y == 0
  }
  
  final def getLifetimeRatio(): Double = {
    (lifeTime.toDouble / life.toDouble)
  }
  
//  override def toString(): String = {
//    "(x: " + pos.x + ", y: " + pos.y + ") - (vx: " + vel.x + ", vy: " + vel.y + ")"
//  }
}