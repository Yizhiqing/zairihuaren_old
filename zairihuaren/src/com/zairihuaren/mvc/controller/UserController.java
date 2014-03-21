package com.zairihuaren.mvc.controller;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zairihuaren.PMF;
import com.zairihuaren.mvc.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getAddCustomerPage(@ModelAttribute("user") User user) {
		return "userManagement/register";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String add(@ModelAttribute("user") User user, ModelMap model) {
		user.setDate(new Date());

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);
		} finally {
			pm.close();
		}

		return "redirect:list";

	}

	@RequestMapping(value = "/update/{name}", method = RequestMethod.GET)
	public String getUpdateCustomerPage(@PathVariable String name,
			HttpServletRequest request, ModelMap model) {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(User.class);
		q.setFilter("name == nameParameter");
		q.setOrdering("date desc");
		q.declareParameters("String nameParameter");

		try {
			List<User> results = (List<User>) q.execute(name);

			if (results.isEmpty()) {
				model.addAttribute("user", null);
			} else {
				model.addAttribute("user", results.get(0));
			}
		} finally {
			q.closeAll();
			pm.close();
		}

		return "userManagement/update";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String key = request.getParameter("key");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			User c = pm.getObjectById(User.class, key);

			c.setName(name);
			c.setEmail(email);
			c.setDate(new Date());

		} finally {

			pm.close();
		}

		// return to list
		return new ModelAndView("redirect:list");

	}

	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			User c = pm.getObjectById(User.class, key);
			pm.deletePersistent(c);

		} finally {
			pm.close();
		}

		// return to list
		return new ModelAndView("redirect:../list");

	}

	// get all customers
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listCustomer(ModelMap model) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		q.setOrdering("date desc");

		List<User> results = null;

		try {
			results = (List<User>) q.execute();

			if (results.isEmpty()) {
				model.addAttribute("userList", null);
			} else {
				model.addAttribute("userList", results);
			}

		} finally {
			q.closeAll();
			pm.close();
		}

		return "userManagement/list";

	}

}