package com.dome.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailDemo {

	public static void main(String[] args) throws Exception {
		
		//1.设置创建session需要的属性
		Properties props=new Properties();
		//设置邮件的传输协议，常用的协议有SMTP、POP3、IMAP4。这里我们使用qq邮箱发送
		//电子百邮件读取协议度(POP3和IMAP)、简单邮件传输协议（SMTP).我们选择stmp协议
		props.setProperty("mail.transport.protocol", "smtp");
		//设置发件人的邮箱的stmp服务地址(qq邮箱的服务地址)
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		//开启请求认证
		props.setProperty("mail.smtp.auth", "true");

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     就需要开启 SSL 安全连接。
        /*
         			SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
                     需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
                         QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
         */
		
		//设置安全认证需要提供一个socketfactory，这个类由java提供
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//设置ssl安全认证的端口
		props.setProperty("mail.smtp.port", "465");
		//只处理ssl的链接，对于非ssl的链接不做处理
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		//设置socketfactory的端口，和ssl安全认证的端口一样就好了
		props.setProperty("mail.smtp.socketFactory.port", "465");
		
		//2.根据配置创建会话对象，用于和邮件服务交互,构造器被私有化，用其他方法创建对象
		Session session=Session.getInstance(props);
		//设置为debug模式，可以查看详细的发送log
		session.setDebug(true);
		
		//3.创建一封邮件，调用自己写的生成邮件的方法
		MimeMessage message=creatMimeMessage(session,"146280xxx@qq.com","18234xxx@qq.com");
		
		//4.根据session创建传输对象
		Transport transport=session.getTransport();
		
		 // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        // 
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
				//常见异常：550 Mailbox not found or access denied.
				//收件人的邮箱注销了或者qq账号不能用了

        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
		//设置连接，账号为发件人，密码为授权码
		transport.connect("hxq1137793660@163.com","vtjhnlvzciqtxxxc");
		//6.发送邮件，发到所有的收件地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		 transport.sendMessage(message, message.getAllRecipients());
		 //7.关闭连接
		 transport.close();
		
	}
	
	
	
		/**
	     * 创建一封只包含文本的简单邮件
	     *
	     * @param session 和服务器交互的会话
	     * @param sendMail 发件人邮箱
	     * @param receiveMail 收件人邮箱
	     * @return MimeMessage
	     * @throws Exception
	     */
		
		//邮件的类型为mime
		public static MimeMessage creatMimeMessage(Session session,String sendMail, String receiveMail) throws Exception{
			//1.创建一封邮件,需要传一个session对象
			MimeMessage message=new MimeMessage(session);
			
			//2.from：发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
			//参数需要传一个address对象，address是接口，用他的实现类
			//三个参数：发件人账号，发件人名字，字符编码
			message.setFrom(new InternetAddress(sendMail, "发件人名字", "UTF-8"));
			
			//3.设置收件人，有三个类型，TO：普通收件人，CC：抄送人，BCC：密送人
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "普通收件人", "UTF-8"));
			//message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(receiveMail, "普通收件人", "UTF-8"));
			//message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(receiveMail, "普通收件人", "UTF-8"));
			
			//4.Subject：邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
			message.setSubject("这是标题","UTF-8");

			//5.Content：邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
			message.setContent("z这是正文.....","text/html;charset=utf-8");
			
			//6.设置发件时间
			message.setSentDate(new Date());
			
			//7.保存设置
			message.saveChanges();
			return message;
		}
		
	
}

