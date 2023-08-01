package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Constant.SignUpMessage;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Form.SignUpForm;
import com.example.demo.Service.SignUpServise;
import com.example.demo.Util.AppUtil;

import lombok.RequiredArgsConstructor;

/**ユーザー登録画面コントローラー 
 * 
 * 
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class SignUpController {

	/**
	 * ユーザー登録Service
	 */
	private final SignUpServise service;

	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 */
	@GetMapping("/signUp")
	public String veiw(Model model, SignUpForm form) {
		return "signUp";

	}

	/**
	 * ログイン
	 * 
	 * @param model モデル
	 * @param form 入力画面
	 * @return 表示画面
	 */

	@PostMapping("/signUp")
	public void signUp(Model model, SignUpForm form) {

		var userInfoOpt = service.resistUserInfo(form);
		
		var signupMessage =  judgeMessageKey(userInfoOpt);

		var messageId = AppUtil.getMessage(messageSource,signupMessage.getMessageId());
		model.addAttribute("message", messageId);
		model.addAttribute("isError", signupMessage.isError());
	}

	private SignUpMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return SignUpMessage.EXISTED_LOGIN_ID;
		} else {
			return SignUpMessage.SUCCEED;
		}
	}

}
