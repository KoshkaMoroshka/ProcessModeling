package ru.sfedu.ProcessModeling.model;

import java.util.ArrayList;

/**
 * Class Simulation
 */
public class Simulation extends SwitchableComponent {

  //
  // Fields
  //

  private ArrayList<Actor> actors;
  private ArrayList<SoundSource> soundSources;
  private ArrayList<Activator> activators;
  private ArrayList<Motion> motions;
  
  //
  // Constructors
  //
  public Simulation () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of actors
   * @param newVar the new value of actors
   */
  public void setActors (ArrayList<Actor> newVar) {
    actors = newVar;
  }

  /**
   * Get the value of actors
   * @return the value of actors
   */
  public ArrayList<Actor> getActors () {
    return actors;
  }

  /**
   * Set the value of soundSources
   * @param newVar the new value of soundSources
   */
  public void setSoundSources (ArrayList<SoundSource> newVar) {
    soundSources = newVar;
  }

  /**
   * Get the value of soundSources
   * @return the value of soundSources
   */
  public ArrayList<SoundSource> getSoundSources () {
    return soundSources;
  }

  /**
   * Set the value of activators
   * @param newVar the new value of activators
   */
  public void setActivators (ArrayList<Activator> newVar) {
    activators = newVar;
  }

  /**
   * Get the value of activators
   * @return the value of activators
   */
  public ArrayList<Activator> getActivators () {
    return activators;
  }

  /**
   * Set the value of motions
   * @param newVar the new value of motions
   */
  public void setMotions (ArrayList<Motion> newVar) {
    motions = newVar;
  }

  /**
   * Get the value of motions
   * @return the value of motions
   */
  public ArrayList<Motion> getMotions () {
    return motions;
  }

  //
  // Other methods
  //

}
