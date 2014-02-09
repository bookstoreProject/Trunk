package producer;

import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessageBundleProducer {
	  @Inject
	  private FacesContext facesContext;
	  @Produces @Messages
	  public ResourceBundle getBundle() {
	    return (ResourceBundle)facesContext.getApplication().getResourceBundle(facesContext, "messages");
	  }
	}
