/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
public class facepainter {
    public facepainter(){}
 @ManagedProperty(value = "#{facepainter}")
  private FacePainter facepainter;

    public FacePainter getFacepainter() {
        return facepainter;
    }

    public void setFacepainter(FacePainter facepainter) {
        this.facepainter = facepainter;
    }
  public void message(){
      facepainter.setPage("messages.xhtml");
  }
}
