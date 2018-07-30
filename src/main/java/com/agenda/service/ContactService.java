package com.agenda.service;

import java.util.List;

import com.agenda.model.ContactModel;

public interface ContactService {
	
	public List<ContactModel> listAll();
	public List<ContactModel> listByUsername(String username);
	public ContactModel getContact(Long id);
	public ContactModel addContact(ContactModel contactModel, String username);
	public ContactModel updateContact(Long id, ContactModel contactModel, String username);
	public void deleteContact(Long id);
	
}
