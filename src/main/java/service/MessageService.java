package service;

import dao.MessageDao;
import model.Message;

import java.util.Set;

public class MessageService {

    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public boolean create(Message message) {
        return messageDao.create(message);
    }

    public Set<Message> getAll(String email) {
        return messageDao.getAll(email);
    }
}
