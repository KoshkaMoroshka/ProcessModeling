package ru.sfedu.ProcessModeling.model;

/**
 * Class SoundSource
 */
public class SoundSource extends SwitchableComponent {

  //
  // Fields
  //

  private String pathToFile;
  private float volume;
  private int position;
  private boolean looping;
  
  //
  // Constructors
  //
  public SoundSource () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of pathToFile
   * @param newVar the new value of pathToFile
   */
  public void setPathToFile (String newVar) {
    pathToFile = newVar;
  }

  /**
   * Get the value of pathToFile
   * @return the value of pathToFile
   */
  public String getPathToFile () {
    return pathToFile;
  }

  /**
   * Set the value of volume
   * @param newVar the new value of volume
   */
  public void setVolume (float newVar) {
    volume = newVar;
  }

  /**
   * Get the value of volume
   * @return the value of volume
   */
  public float getVolume () {
    return volume;
  }

  /**
   * Set the value of position
   * @param newVar the new value of position
   */
  public void setPosition (int newVar) {
    position = newVar;
  }

  /**
   * Get the value of position
   * @return the value of position
   */
  public int getPosition () {
    return position;
  }

  /**
   * Set the value of looping
   * @param newVar the new value of looping
   */
  public void setLooping (boolean newVar) {
    looping = newVar;
  }

  /**
   * Get the value of looping
   * @return the value of looping
   */
  public boolean getLooping () {
    return looping;
  }

  //
  // Other methods
  //

}
