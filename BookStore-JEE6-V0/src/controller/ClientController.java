package controller;

import java.io.Serializable;
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

import dto.OrderDTO;
import producer.LoggedIn;
import service.ClientService;
import model.ClientManager;
import model.ClientServiceEJB;
import entities.Client;
import entities.Order;
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

	private Order order;
	
	private List<OrderDTO> ordersDTO;
	
	@Named
	public Order getOrder() {
		return order;
	}
	
	@Produces
	@LoggedIn
	@Named
	public Client getCurrentClient() {
		return currentClient;
	}
	
	@Named
	public List<OrderDTO> getOrdersDTO() {
		return ordersDTO;
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
		return "welcome";
	}
	
	public String account() {
		for(Order o : currentClient.getCommandes()){
			//OrderDTO orderDto = new OrderDTO();
			System.out.println(o.getItems().get(0).getId());
			System.out.println("chargement ok");
			//orderDto.getItems().addAll(o.getItems());
			//orderDto.setDate(o.getDate());
			//orderDto.setTotal(o.getTotal());
			//ordersDTO.add(orderDto);
		}
		return "account";
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
