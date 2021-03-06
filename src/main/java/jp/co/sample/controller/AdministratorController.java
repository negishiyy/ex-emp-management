package jp.co.sample.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")

public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	@RequestMapping("/insert")     
	private String insert(@Validated InsertAdministratorForm form, BindingResult result, RedirectAttributes redirectAttributes) {

		if(result.hasErrors()) {
			return toInsert();
		}
		
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);

		return "redirect:/";
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	@Autowired
	private HttpSession session;



	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator loginInfo =  administratorService.login(form.getMailAddress(), form.getPassword());

		if(loginInfo == null) {
			model.addAttribute("loginError" ,"???????????????????????????????????????????????????????????????");
			return "administrator/login";
		}else {
			session.setAttribute("administratorName", loginInfo);
			return "forward:/employee/showList";   
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";

	}


}
