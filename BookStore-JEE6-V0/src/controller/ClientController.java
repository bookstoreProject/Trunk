package controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIInput;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ClientService;
import model.ClientManager;
import model.ClientServiceEJB;
import entities.Client;
import form.LoginForm;

@Named
// Avec CDI remplace l'annotation ManagedBean
@SessionScoped
public class ClientController implements Serializable {
	@Inject
	private LoginForm loginForm;
	// @Inject
	// private ClientManager clientManager;
	@Inject
	private ClientService clientService;
	
	@Inject
	private MessageBean messageBean;

	private Logger log = LoggerFactory.getLogger(ClientController.class);

	private Client currentClient;
	
	private UIInput UiLogin;

	@Produces
	@Named
	public Client getCurrentClient() {
		return currentClient;
	}

	public String doLogin() {
		// currentClient =
		// clientManager.login(loginForm.getLogin(),loginForm.getPassword());
		currentClient = clientService.login(loginForm.getLogin(), loginForm.getPassword());
		if (currentClient == null) {
			messageBean.addMessage(UiLogin,"clientNotFound");
			return null;
		}
		return "welcome";
	}

	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		currentClient = null;
		return "welcome";
	}

	public UIInput getUiLogin() {
		return UiLogin;
	}

	public void setUiLogin(UIInput uiLogin) {
		UiLogin = uiLogin;
	}
}
