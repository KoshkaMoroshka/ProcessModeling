package ru.sfedu.ProcessModeling.model;

/**
 * Class Collider
 */
public class Collider extends Activator {

  //
  // Fields
  //

  private long idActor;
  
  //
  // Constructors
  //
  public Collider () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of idActor
   * @param newVar the new value of idActor
   */
  public void setIdActor (long newVar) {
    idActor = newVar;
  }

  /**
   * Get the value of idActor
   * @return the value of idActor
   */
  public long getIdActor () {
    return idActor;
  }

  //
  // Other methods
  //

}
