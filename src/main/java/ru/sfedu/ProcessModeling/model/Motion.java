package ru.sfedu.ProcessModeling.model;

import ru.sfedu.ProcessModeling.model.enums.MotionType;

/**
 * Class Motion
 */
public class Motion extends SwitchableComponent {

  //
  // Fields
  //

  private Actor motiveActor;
  private Actor targetActor;
  private MotionType motionType;
  private float speed;
  private float radius;
  
  //
  // Constructors
  //
  public Motion () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of motiveActor
   * @param newVar the new value of motiveActor
   */
  public void setMotiveActor (Actor newVar) {
    motiveActor = newVar;
  }

  /**
   * Get the value of motiveActor
   * @return the value of motiveActor
   */
  public Actor getMotiveActor () {
    return motiveActor;
  }

  /**
   * Set the value of targetActor
   * @param newVar the new value of targetActor
   */
  public void setTargetActor (Actor newVar) {
    targetActor = newVar;
  }

  /**
   * Get the value of targetActor
   * @return the value of targetActor
   */
  public Actor getTargetActor () {
    return targetActor;
  }

  /**
   * Set the value of motionType
   * @param newVar the new value of motionType
   */
  public void setMotionType (MotionType newVar) {
    motionType = newVar;
  }

  /**
   * Get the value of motionType
   * @return the value of motionType
   */
  public MotionType getMotionType () {
    return motionType;
  }

  /**
   * Set the value of speed
   * @param newVar the new value of speed
   */
  public void setSpeed (float newVar) {
    speed = newVar;
  }

  /**
   * Get the value of speed
   * @return the value of speed
   */
  public float getSpeed () {
    return speed;
  }

  /**
   * Set the value of radius
   * @param newVar the new value of radius
   */
  public void setRadius (float newVar) {
    radius = newVar;
  }

  /**
   * Get the value of radius
   * @return the value of radius
   */
  public float getRadius () {
    return radius;
  }

  //
  // Other methods
  //

}
