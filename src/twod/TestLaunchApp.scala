package twod

import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import twod.particles.ParticleEmitter
import twod.util.Vec2

/*
 * Todo List:
 * - Particle Emitters
 * - Change in size
 * - Change in rotation
 * - Change in color [-/]
 */

object PlanetsApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "2d Test App"
    
    scene = new Scene(800,600) {
      val canvas = new Canvas(800,600)
      val g = canvas.graphicsContext2D
      content = canvas
      
      var mx = 0.0
      var my = 0.0
      
      var emitters: List[ParticleEmitter] = List()
      val p = new Player(new Vec2(300, 300))
      
      canvas.onMouseMoved = (me: MouseEvent) => {
        mx = me.x
        my = me.y
      }
      
      canvas.onMouseClicked = (me: MouseEvent) => {
        val x = me.x
        val y = me.y
        
        var emitter = new ParticleEmitter(new Vec2(x, y), 50, true, true, 5)
        
        emitters = emitters.::(emitter)
      }
      
      var priorTime:Long = 0
      val timer = AnimationTimer(t => {
        if(t - priorTime > 1e9/60) {
          priorTime = t
          
          g.fill = Color.BLANCHEDALMOND
          g.fillRect(0, 0, 800, 600)
          
          p.display(g)
          p.timeStep()
          
          for(e <- emitters) {
            e.timeStep(mx, my)
            e.display(g)
          }
          
          var totalParticles = 0
          for(e <- emitters) {
            totalParticles += e.size
          }
//          println(totalParticles)
        }
      })
      timer.start
    }
  }  
}