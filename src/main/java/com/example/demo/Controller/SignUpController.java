package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Constant.MessageConst;
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
	 * ユーザー登録
	 * 
	 * @param model モデル
	 * @param form 入力画面
	 * @param bdResult 入力チェック結果
	 * @return 表示画面
	 */

	@PostMapping("/signUp")
	public void signUp(Model model, @Validated SignUpForm form, BindingResult bdResult) {
		if (bdResult.hasErrors()) {

			editGuideMessage(model, MessageConst.FORM_ERROR, true);
			return;
		}
		var userInfoOpt = service.resistUserInfo(form);

		var signupMessage = judgeMessageKey(userInfoOpt);

		editGuideMessage(model, signupMessage.getMessageId(), signupMessage.isError());
	}

	/**
	 * 画面に表示するガイドメッセージを設定する
	 * 
	 * @param model モデル
	 * @param messageId メッセージID
	 * @param isError エラー有無
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		var message = AppUtil.getMessage(messageSource, messageId);
		model.addAttribute("message", message);
		model.addAttribute("isError", isError);
	}

	/**
	 * ユーザー情報登録の結果メッセージキーを判断する
	 * 
	 * 
	 * @param userInfoOpt ユーザー登録結果（登録済みだった場合はEmpty）
	 * @return メッセージキー
	 */
	private SignUpMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return SignUpMessage.EXISTED_LOGIN_ID;
		} else {
			return SignUpMessage.SUCCEED;
		}
	}

}
