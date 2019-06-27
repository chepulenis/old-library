package dao;

import model.Client;

import java.util.List;

public interface ClientDAO {
    public void createClient (Client client);
    public void editClient(Client client);
    public void removeClient (int id);
    public List<Client> listAllClients();
    public Client getClientById (int id);
}
