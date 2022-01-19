package com.kumaran.student;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
	@Autowired
	StudentService service;

	@RequestMapping("/about")
	public String about() {
		return "about_page";
	}

	@GetMapping("/register")
	public String showForm(Model model) {
		Student stu = new Student();
		model.addAttribute("stu", stu);
		return "register_form";
	}

	@PostMapping("/register")
	public String submitForm(@Valid @ModelAttribute("stu") Student stu, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "register_form";
		}
		service.save(stu);
		return "redirect:/view";

	}

	@RequestMapping("/view")
	public String viewHomePage(Model model) {
		List<Student> stuList = service.listAll();
		model.addAttribute("list", stuList);
		return "register_success";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("stu") Student stu) {
		service.save(stu);

		return "redirect:/view";
	}

	@RequestMapping("/edit/{roll}")
	public String showEditProductPage(@PathVariable("roll") Long roll, Model model) {

		Student stu = service.get(roll);
		model.addAttribute("stu", stu);
		return "edit_user";
	}

	@RequestMapping("/delete/{roll}")
	public String deleteProduct(@PathVariable("roll") Long roll) {
		service.delete(roll);
		return "redirect:/view";
	}
	@RequestMapping("jspview")
	public String jsp() {
		return "home.jsp";
	}
}
