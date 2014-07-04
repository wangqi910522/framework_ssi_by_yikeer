package com.ht.yikecrm.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleMailSender {
	
	protected static final Log LOG = LogFactory.getLog(SimpleMailSender.class);
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 * @throws UnsupportedEncodingException
	 */
//	public static boolean sendTextMail(MailSenderInfo mailInfo)
//			throws UnsupportedEncodingException {
//		// 判断是否需要身份认证
//		MyAuthenticator authenticator = null;
//		Properties pro = mailInfo.getProperties();
//		if (mailInfo.isValidate()) {
//			// 如果需要身份认证，则创建一个密码验证器
//			authenticator = new MyAuthenticator(mailInfo.getUserName(),
//					mailInfo.getPassword());
//		}
//		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
//		Session sendMailSession = Session
//				.getDefaultInstance(pro, authenticator);
//		sendMailSession.setDebug(true);
//		try {
//			// 根据session创建一个邮件消息
//			Message mailMessage = new MimeMessage(sendMailSession);
//			// 创建邮件发送者地址
//			Address from = new InternetAddress(mailInfo.getFromAddress(),
//					mailInfo.getFromAddressName());
//			// 设置邮件消息的发送者
//			mailMessage.setFrom(from);
//			// 创建邮件的接收者地址，并设置到邮件消息中
//			Address to = new InternetAddress(mailInfo.getToAddress());
//			mailMessage.setRecipient(Message.RecipientType.TO, to);
//			// 设置邮件消息的主题
//			mailMessage.setSubject(mailInfo.getSubject());
//			// 设置邮件消息发送的时间
//			mailMessage.setSentDate(new Date());
//			// 设置邮件消息的主要内容
//			String mailContent = mailInfo.getContent();
//			//mailMessage.setText(mailContent);
//			mailMessage.setContent(mailContent, "text/plain; charset=utf-8");
//			//mailMessage.setHeader("Sender", mailInfo.getFromAddressName());			
//			// 发送邮件
////			Transport ts = sendMailSession.getTransport("smtp");
////			ts.connect(mailInfo.getMailServerHost(), mailInfo.getUserName(),
////					mailInfo.getPassword());
////			ts.send(mailMessage);
////			ts.close();
//			Transport.send(mailMessage);
//			LOG.debug("==mailto:"+mailInfo.getToAddress()+"===subject:"+mailInfo.getSubject()+"===sucess");
//			return true;
//		} catch (MessagingException ex) {
//			LOG.error(ex);
//		}
//		return false;
//	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 * @throws UnsupportedEncodingException
	 */
//	public static boolean sendHtmlMail(MailSenderInfo mailInfo)
//			throws UnsupportedEncodingException {
//		// 判断是否需要身份认证
//		MyAuthenticator authenticator = null;
//		Properties pro = mailInfo.getProperties();
//		// 如果需要身份认证，则创建一个密码验证器
//		if (mailInfo.isValidate()) {
//			authenticator = new MyAuthenticator(mailInfo.getUserName(),
//					mailInfo.getPassword());
//		}
//		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
//		Session sendMailSession = Session
//				.getDefaultInstance(pro, authenticator);
//		try {
//			// 根据session创建一个邮件消息
//			Message mailMessage = new MimeMessage(sendMailSession);
//			// 创建邮件发送者地址
//			Address from = new InternetAddress(mailInfo.getFromAddress(),
//					mailInfo.getFromAddressName());
//			// 设置邮件消息的发送者
//			mailMessage.setFrom(from);
//			// 创建邮件的接收者地址，并设置到邮件消息中
//			Address to = new InternetAddress(mailInfo.getToAddress());
//			// Message.RecipientType.TO属性表示接收者的类型为TO
//			mailMessage.setRecipient(Message.RecipientType.TO, to);
//			// 设置邮件消息的主题
//			mailMessage.setSubject(mailInfo.getSubject());
//			// 设置邮件消息发送的时间
//			mailMessage.setSentDate(new Date());
//			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
//			Multipart mainPart = new MimeMultipart();
//			// 创建一个包含HTML内容的MimeBodyPart
//			BodyPart html = new MimeBodyPart();
//			// 设置HTML内容
//			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
//			mainPart.addBodyPart(html);
//			// 将MiniMultipart对象设置为邮件内容
//			mailMessage.setContent(mainPart);
//			// 发送邮件			
//			Transport.send(mailMessage);
//			LOG.debug("==mailto:"+mailInfo.getToAddress()+"===subject:"+mailInfo.getSubject()+"===sucess");
//			return true;
//		} catch (MessagingException ex) {
//			LOG.error(ex);
//		}
//		return false;
//	}

//	public static void main(String[] args) throws UnsupportedEncodingException {
//		// TODO Auto-generated method stub
//		// 这个类主要是设置邮件　　
//		MailSenderInfo mailInfo = new MailSenderInfo();
//		mailInfo.setValidate(true);
//		mailInfo.setFromAddress("htykcrm@126.com"); // 设置发送邮箱的话， 需要同时设置用户名和密码
//		mailInfo.setFromAddressName("一个朋友");
//		mailInfo.setToAddress("eboge@sohu.com");
//		mailInfo.setSubject("邮箱标题---YIKECRM用户激活");
//		mailInfo.setContent("您的激活吗：".concat("测试的"));
//		SimpleMailSender.sendTextMail(mailInfo);
//		System.out.println("邮件发送完成");
//	}

}
