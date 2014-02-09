package controller;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import producer.Messages;

@Named
@RequestScoped
public class MessageBean {
  @Inject
  private FacesContext facesContext;
  @Inject @Messages
  private ResourceBundle bundle;
  
  public String getMessage(String key){
     return bundle.getString(key);
  }
  public void addMessage(String key){
    String summary = getMessage(key);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, summary);
    facesContext.addMessage(null, msg);
  }
}

