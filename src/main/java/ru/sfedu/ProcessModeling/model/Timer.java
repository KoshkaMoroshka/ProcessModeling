package ru.sfedu.ProcessModeling.model;

/**
 * Class Timer
 */
public class Timer extends Activator {

  //
  // Fields
  //

  private float time;
  
  //
  // Constructors
  //
  public Timer () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of time
   * @param newVar the new value of time
   */
  public void setTime (float newVar) {
    time = newVar;
  }

  /**
   * Get the value of time
   * @return the value of time
   */
  public float getTime () {
    return time;
  }

  //
  // Other methods
  //

}
