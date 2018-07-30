package com.agenda.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.converter.ContactConverter;
import com.agenda.entity.Contact;
import com.agenda.entity.User;
import com.agenda.model.ContactModel;
import com.agenda.repository.ContactRepository;
import com.agenda.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactConverter contactConverter;

	@Override
	public List<ContactModel> listAll() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<>();
		for (Contact c : contacts) {
			contactsModel.add(contactConverter.EntityToModel(c));
		}
		return contactsModel;
	}

	@Override
	public List<ContactModel> listByUsername(String username) {
		List<Contact> contacts = contactRepository.findByUser_username(username);
		List<ContactModel> contactsModel = new ArrayList<>();
		for (Contact c : contacts) {
			contactsModel.add(contactConverter.EntityToModel(c));
		}
		return contactsModel;
	}

	@Override
	public ContactModel getContact(Long id) {
		return contactConverter.EntityToModel(contactRepository.findById(id).get());
	}

	@Override
	public ContactModel addContact(ContactModel contactModel, String username) {
		Contact contact = contactConverter.ModelToEntity(contactModel);
		User user = new User();
		user.setUsername(username);
		contact.setUser(user);
		return contactConverter.EntityToModel(contactRepository.save(contact));
	}

	@Override
	public ContactModel updateContact(Long id, ContactModel contactModel, String username) {
		ContactModel model = getContact(id);
		if (null != model) {
			model.setFirstname(contactModel.getFirstname());
			model.setLastname(contactModel.getLastname());
			model.setTelephone(contactModel.getTelephone());
			model.setCity(contactModel.getCity());
			return addContact(model,username);
		}
		return null;
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}

}
