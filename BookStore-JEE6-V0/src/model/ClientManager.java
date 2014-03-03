package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import entities.Client;
/*
 * Test Marc 1
 */
@Stateless
public class ClientManager {
  @PersistenceContext
  private EntityManager em;

  public Client login(String login, String password) {
    Client client = null;
    try {
      client = (Client) em.createQuery("select c from Client c where c.login=:login and c.password=:password")
          .setParameter("login", login).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
    }
    return client;
  }
}
