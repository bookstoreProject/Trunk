package producer;

import java.util.ResourceBundle;


import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessageBundleProducer {

	@Produces
	@Messages
	public ResourceBundle getBundle() {
		return (ResourceBundle) FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
	}
}
