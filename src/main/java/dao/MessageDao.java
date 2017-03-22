package dao;

import model.Message;

import java.util.Set;

public interface MessageDao {
    Set<Message> getAll(String email);
    boolean create(Message message);
}
