package com.agenda.service;

import java.util.List;

import com.agenda.model.ContactModel;

public interface ContactService {
	
	public List<ContactModel> listAll();
	public ContactModel getContact(Long id);
	public ContactModel addContact(ContactModel contactModel);
	public ContactModel updateContact(Long id, ContactModel contactModel);
	public void deleteContact(Long id);
	
}
