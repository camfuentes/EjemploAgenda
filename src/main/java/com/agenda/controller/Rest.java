package com.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.model.ContactModel;
import com.agenda.service.ContactService;

@RestController
@RequestMapping("/rest")
public class Rest {

	@Autowired
	private ContactService contactService;

	@GetMapping
	@ResponseBody
	public List<ContactModel> getAll() {
		return contactService.listAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ContactModel getContact(@PathVariable Long id) {
		return contactService.getContact(id);
	}
	
	@PostMapping
	@ResponseBody
	public List<ContactModel> addContact(@RequestBody ContactModel contactModel){
		contactService.addContact(contactModel);
		return contactService.listAll();
	}
	
	@PutMapping("/{id}")
	public void updateContact (@PathVariable Long id, @RequestBody ContactModel contactModel) {
		contactService.updateContact(id, contactModel);
	}
	
	@DeleteMapping("/{id}")
	public void deleteContact (@PathVariable Long id) {
		contactService.deleteContact(id);
	}
	
}
