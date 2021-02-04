package ru.sfedu.ProcessModeling.model;

import ru.sfedu.ProcessModeling.model.enums.ActorType;

/**
 * Class Actor
 */
public class Actor extends SwitchableComponent {

  //
  // Fields
  //

  private String name;
  private ActorType actorType;
  private float x;
  private float y;
  private float width;
  private float height;
  private float rotaton;
  private int color;
  
  //
  // Constructors
  //
  public Actor () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of name
   * @param newVar the new value of name
   */
  public void setName (String newVar) {
    name = newVar;
  }

  /**
   * Get the value of name
   * @return the value of name
   */
  public String getName () {
    return name;
  }

  /**
   * Set the value of actorType
   * @param newVar the new value of actorType
   */
  public void setActorType (ActorType newVar) {
    actorType = newVar;
  }

  /**
   * Get the value of actorType
   * @return the value of actorType
   */
  public ActorType getActorType () {
    return actorType;
  }

  /**
   * Set the value of x
   * @param newVar the new value of x
   */
  public void setX (float newVar) {
    x = newVar;
  }

  /**
   * Get the value of x
   * @return the value of x
   */
  public float getX () {
    return x;
  }

  /**
   * Set the value of y
   * @param newVar the new value of y
   */
  public void setY (float newVar) {
    y = newVar;
  }

  /**
   * Get the value of y
   * @return the value of y
   */
  public float getY () {
    return y;
  }

  /**
   * Set the value of width
   * @param newVar the new value of width
   */
  public void setWidth (float newVar) {
    width = newVar;
  }

  /**
   * Get the value of width
   * @return the value of width
   */
  public float getWidth () {
    return width;
  }

  /**
   * Set the value of height
   * @param newVar the new value of height
   */
  public void setHeight (float newVar) {
    height = newVar;
  }

  /**
   * Get the value of height
   * @return the value of height
   */
  public float getHeight () {
    return height;
  }

  /**
   * Set the value of rotaton
   * @param newVar the new value of rotaton
   */
  public void setRotaton (float newVar) {
    rotaton = newVar;
  }

  /**
   * Get the value of rotaton
   * @return the value of rotaton
   */
  public float getRotaton () {
    return rotaton;
  }

  /**
   * Set the value of color
   * @param newVar the new value of color
   */
  public void setColor (int newVar) {
    color = newVar;
  }

  /**
   * Get the value of color
   * @return the value of color
   */
  public int getColor () {
    return color;
  }

  //
  // Other methods
  //

}
