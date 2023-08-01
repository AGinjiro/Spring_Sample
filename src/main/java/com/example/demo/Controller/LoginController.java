package com.example.demo.Controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Constant.MessageConst;
import com.example.demo.Form.LoginForm;
import com.example.demo.Service.LoginService;
import com.example.demo.Util.AppUtil;

import lombok.RequiredArgsConstructor;



/**
 *ログインコントローラー 
 * 
 * 
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	/**
	 * ログインService
	 */
	private final LoginService service;
	
	
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * メッセージソース
	 */
	private final MessageSource messageSource;
	
	
	/**
	 * 初期表示
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@GetMapping("/login")
	public String veiw(Model model, LoginForm form) {
		return "login";

	}
	
	
	/**
	 * ログイン
	 * 
	 * @param model モデル
	 * @param form 入力画面
	 * @return 表示画面
	 */
	
	
	@PostMapping("/login")
	public String login(Model model,LoginForm form) {
 
		var userInfo = service.searchUserById(form.getLoginId());
		
		
		var isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(form.getPassword(),userInfo.get().getPassword());
		
		if (isCorrectUserAuth) {
			return "redirect:/menu";

		} else {
			var errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}
	}
	
	

}
