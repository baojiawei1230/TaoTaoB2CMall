package com.taotao.user.utils;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSendUtil {
	// 收件userinfo@taotao.com 发件taotao@taotao.com 密码taotao
	public static String sendMail(String mail) {
		int identifyCode = 0;
		// 建立连接Session HttpSession
		Properties properties = new Properties();
		// Properties 给连接邮箱会话session 配置邮件参数信息
		properties.setProperty("mail.smtp.host", "localhost");// 配置发送邮件服务器协议,当前为本地
		properties.setProperty("mail.transport.protocol", "smtp");// 传输协议名称 stmp
		Session session = Session.getDefaultInstance(properties);// 连接上 邮件服务器
		// 编写Message 邮件对象
		Message message = new MimeMessage(session);
		try {
			// 设置发件人 Address
			message.setFrom(new InternetAddress("taotao@taotao.com"));
			// 设置收件人RecipientType.TO 发送
			message.setRecipient(RecipientType.TO, new InternetAddress(mail));
			// 设置邮件主题
			message.setSubject("taotao验证");
			// 设置内容 随机生成6位数验证码
			identifyCode = (int) (Math.random() * 1000000 + 100000);
			message.setContent("您好，淘淘商城欢迎您，验证码为" + identifyCode,
					"text/html;charset=utf-8");
			// 通过Transport发送
			Transport transport = session.getTransport();
			transport.connect("taotao", "taotao");
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return identifyCode + "";

	}

}
