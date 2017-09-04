package twod.particles

import twod.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class ParticleEmitter(protected val origin: Vec2, protected val duration: Int, protected val loop: Boolean, 
                      protected val random: Boolean, protected val rate: Int) {
  
  protected var particles = List[Particle]()
  protected var durationTime = (duration - 0)
  protected var acceleration = new Vec2(0, .3)
  
  protected var sangle = 0.0
  protected var srate = .1
  
  def timeStep(mx: Double, my: Double): Unit = {
    if(!shouldRemove) {
      if(!loop) {
        durationTime -= 1
      }
      
      var a = new Vec2(mx, my)
      
      for(i <- 0 until rate) {
        
//        if(sangle >= 2 * math.Pi) {
//          sangle = 0
//        }else{
//          sangle += srate
//        }
        
//        var angle = ((2 * math.Pi) - 0.0) * scala.util.Random.nextDouble();
        var angle = math.Pi
        
        val vel = 9
        val ax = vel * math.sin(angle + util.Random.nextDouble() - .5)
        val ay = vel * math.cos(angle)
        
        if(random) {
          particles = particles.::(
              new RainbowParticle(new Vec2(origin.x, origin.y), new Vec2(ax, ay), util.Random.nextInt(10) + 5, 100))
        }else{
          particles = particles.::(
              new RainbowParticle(new Vec2(origin.x, origin.y), new Vec2(ax, ay), 5, 100))
        }
      }
    }
    
    if(size > 0) {
      particles.foreach(x => x.timeStep())
      
      if(acceleration != null) {
        particles.foreach(x => x.applyAcceleration(acceleration))
      }
      
      particles = particles.filter(x => !x.shouldRemove)
    }else{
      if(loop) {
        durationTime = duration
      }
    }
  }
  
  def display(g: GraphicsContext) {
    particles.foreach(x => x.display(g))
  }
  
  def shouldRemove(): Boolean = {
    durationTime == 0
  }
  
  def size(): Int = {
    particles.size
  }
  
  override def toString(): String = {
    "size: " + size + ", duration: " + durationTime
  }
}