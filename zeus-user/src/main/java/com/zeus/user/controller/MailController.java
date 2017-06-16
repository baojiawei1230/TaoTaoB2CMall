package com.zeus.user.controller;

import com.taotao.common.service.RedisService;
import com.taotao.userinfo.utils.MailSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mail")
public class MailController {
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value = "password" , method = RequestMethod.GET)
	public ModelAndView password(){
	    ModelAndView mv = new ModelAndView("lose-password");
	    return mv ;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sendEmail(@RequestParam("emailinfo") String email) {
		String identifyCode = MailSendUtil.sendMail(email);
		redisService.set("IDENTIFYCODE", identifyCode);
		ModelAndView modelAndView = new ModelAndView("email");
		return modelAndView;
	}

	@RequestMapping(value = "identifyCode", method = RequestMethod.GET)
	public ModelAndView SendidentifyCode(
			@RequestParam("identifyCode") String identifyCode) {
		if (redisService.get("IDENTIFYCODE").equals(identifyCode)) {
			ModelAndView modelAndView = new ModelAndView("success");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("email");
			modelAndView.addObject("error", "您的验证码输入有误 ，请重新输入");
			return modelAndView;
		}
	}

}
