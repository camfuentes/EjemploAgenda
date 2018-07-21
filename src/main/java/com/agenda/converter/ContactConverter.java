package com.agenda.converter;

import org.springframework.stereotype.Component;

import com.agenda.entity.Contact;
import com.agenda.model.ContactModel;

@Component
public class ContactConverter {
	
	public Contact ModelToEntity(ContactModel contactModel) {
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFistname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		return contact;
	}
	
	public ContactModel EntityToModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirstname(contact.getFistname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setCity(contact.getCity());
		return contactModel;
	}
}
