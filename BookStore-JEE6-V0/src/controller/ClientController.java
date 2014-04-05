package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIInput;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import producer.LoggedIn;
import service.ClientService;
import service.OrderService;
import model.ClientManager;
import model.ClientServiceEJB;
import entities.Client;
import entities.Order;
import entities.OrderItem;
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
	
	@Inject 
	OrderService orderService;

	private Logger log = LoggerFactory.getLogger(ClientController.class);

	private Client currentClient;
	
	private UIInput UiLogin;

	private Order order;
	
	private Order accountDetail;
	
	public Order getAccountDetail() {
		return accountDetail;
	}

	public Order getOrder() {
		return order;
	}
	
	@Produces
	@LoggedIn
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
		order = new Order();
		order.setClient(currentClient);
		order.setDate(new Date());
		return "welcome";
	}
	
	public String account() {
		currentClient.setCommandes(orderService.getOrders(currentClient));
		//getOrder();
		return "account";
	}
	
	public List getOrders(){
		return orderService.getOrders(currentClient);
	}
	
	public String doAccountDetail(Long id) {
		accountDetail = orderService.find(id);
		return "account-detail";
	}
	
	
	public String doRegister() {
		return "register";
	}
	
	public String doRegisterNewAccount(){
		Client c = new Client();
		c.setLogin(loginForm.getLogin());
		c.setPassword(loginForm.getPassword());
		clientService.create(c);
		return doLogin();
	}
	
	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		order = null; 
		accountDetail = null;
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
