package ru.sfedu.ProcessModeling.model;

import ru.sfedu.ProcessModeling.model.enums.ActivatorType;
import ru.sfedu.ProcessModeling.model.enums.ComponentType;

/**
 * Class Activator
 */
public class Activator extends SwitchableComponent {

  //
  // Fields
  //

  private ActivatorType typeActivator;
  private long targetId;
  private ComponentType targetType;
  
  //
  // Constructors
  //
  public Activator () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of typeActivator
   * @param newVar the new value of typeActivator
   */
  public void setTypeActivator (ActivatorType newVar) {
    typeActivator = newVar;
  }

  /**
   * Get the value of typeActivator
   * @return the value of typeActivator
   */
  public ActivatorType getTypeActivator () {
    return typeActivator;
  }

  /**
   * Set the value of targetId
   * @param newVar the new value of targetId
   */
  public void setTargetId (long newVar) {
    targetId = newVar;
  }

  /**
   * Get the value of targetId
   * @return the value of targetId
   */
  public long getTargetId () {
    return targetId;
  }

  /**
   * Set the value of targetType
   * @param newVar the new value of targetType
   */
  public void setTargetType (ComponentType newVar) {
    targetType = newVar;
  }

  /**
   * Get the value of targetType
   * @return the value of targetType
   */
  public ComponentType getTargetType () {
    return targetType;
  }

  //
  // Other methods
  //

}
