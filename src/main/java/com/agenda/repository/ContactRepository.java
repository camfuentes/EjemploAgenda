package com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	public List<Contact> findByUser_username(String username);
}
