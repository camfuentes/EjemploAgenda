package com.agenda.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.constant.ViewConstant;
import com.agenda.model.ContactModel;
import com.agenda.service.ContactService;

@Controller
@RequestMapping("/contacts")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // permitAll()
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@Autowired
	private ContactService contactService;

	@GetMapping
	public ModelAndView contacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("contactModel", contactService.listByUsername(user.getUsername()));
		mav.addObject("username", user.getUsername());
		return mav;
	}

	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name = "id", required = false) Long id, Model model) {
		LOG.info("METHOD : redirectContactForm() -- PARAMS : id='" + id + "'");
		ContactModel contact = new ContactModel();
		if (id != null) {
			contact = contactService.getContact(id);
		}
		model.addAttribute("contactModel", contact);
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute("contactModel") ContactModel contactModel, RedirectAttributes attributes) {
		LOG.info("METHOD : addContact() -- PARAMS : '" + contactModel.toString() + "'");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ContactModel contact = contactService.addContact(contactModel, user.getUsername());
		if (null != contact) {
			if (contactModel.getId() == null) {
				attributes.addFlashAttribute("result", "Contact Added Successfully");
				return "redirect:/" + ViewConstant.CONTACTS;
			} else {
				attributes.addFlashAttribute("result", "Contact Updated Successfully");
				return "redirect:/" + ViewConstant.CONTACTS;
			}
		}
		attributes.addFlashAttribute("error", "Error Adding contact");
		return "redirect:/" + ViewConstant.CONTACTS;
	}

	@PostMapping("/delete")
	public String deleteContact(@RequestParam(name = "id", required = true) Long id) {
		LOG.info("METHOD : deleteContact() -- PARAMS : id='" + id.toString() + "'");
		contactService.deleteContact(id);
		return "redirect:/" + ViewConstant.CONTACTS;
	}

}
