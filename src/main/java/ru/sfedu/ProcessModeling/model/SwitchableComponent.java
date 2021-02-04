package ru.sfedu.ProcessModeling.model;

/**
 * Class SwitchableComponent
 */
public class SwitchableComponent {

  //
  // Fields
  //

  private long id;
  private boolean active;
  
  //
  // Constructors
  //
  public SwitchableComponent () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (long newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public long getId () {
    return id;
  }

  /**
   * Set the value of active
   * @param newVar the new value of active
   */
  public void setActive (boolean newVar) {
    active = newVar;
  }

  /**
   * Get the value of active
   * @return the value of active
   */
  public boolean getActive () {
    return active;
  }

  //
  // Other methods
  //

  /**
   * @param        id
   * @param        active
   */
  public void switchActive(long id, boolean active)
  {
  }


}
