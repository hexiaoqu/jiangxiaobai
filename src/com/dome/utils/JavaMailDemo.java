package com.dome.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailDemo {

	public static void main(String[] args) throws Exception {
		
		//1.���ô���session��Ҫ������
		Properties props=new Properties();
		//�����ʼ��Ĵ���Э�飬���õ�Э����SMTP��POP3��IMAP4����������ʹ��qq���䷢��
		//���Ӱ��ʼ���ȡЭ���(POP3��IMAP)�����ʼ�����Э�飨SMTP).����ѡ��stmpЭ��
		props.setProperty("mail.transport.protocol", "smtp");
		//���÷����˵������stmp�����ַ(qq����ķ����ַ)
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		//����������֤
		props.setProperty("mail.smtp.auth", "true");

        // PS: ĳЩ���������Ҫ�� SMTP ������Ҫʹ�� SSL ��ȫ��֤ (Ϊ����߰�ȫ��, ����֧��SSL����, Ҳ�����Լ�����),
        //     ����޷������ʼ�������, ��ϸ�鿴����̨��ӡ�� log, ����������� ������ʧ��, Ҫ�� SSL ��ȫ���ӡ� �ȴ���,
        //     ����Ҫ���� SSL ��ȫ���ӡ�
        /*
         			SMTP �������Ķ˿� (�� SSL ���ӵĶ˿�һ��Ĭ��Ϊ 25, ���Բ����, ��������� SSL ����,
                     ��Ҫ��Ϊ��Ӧ����� SMTP �������Ķ˿�, ����ɲ鿴��Ӧ�������İ���,
                         QQ�����SMTP(SLL)�˿�Ϊ465��587, ������������ȥ�鿴)
         */
		
		//���ð�ȫ��֤��Ҫ�ṩһ��socketfactory���������java�ṩ
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//����ssl��ȫ��֤�Ķ˿�
		props.setProperty("mail.smtp.port", "465");
		//ֻ����ssl�����ӣ����ڷ�ssl�����Ӳ�������
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		//����socketfactory�Ķ˿ڣ���ssl��ȫ��֤�Ķ˿�һ���ͺ���
		props.setProperty("mail.smtp.socketFactory.port", "465");
		
		//2.�������ô����Ự�������ں��ʼ����񽻻�,��������˽�л���������������������
		Session session=Session.getInstance(props);
		//����Ϊdebugģʽ�����Բ鿴��ϸ�ķ���log
		session.setDebug(true);
		
		//3.����һ���ʼ��������Լ�д�������ʼ��ķ���
		MimeMessage message=creatMimeMessage(session,"146280xxx@qq.com","18234xxx@qq.com");
		
		//4.����session�����������
		Transport transport=session.getTransport();
		
		 // 5. ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
        // 
        //    PS_01: �ɰܵ��жϹؼ��ڴ�һ��, ������ӷ�����ʧ��, �����ڿ���̨�����Ӧʧ��ԭ��� log,
        //           ��ϸ�鿴ʧ��ԭ��, ��Щ����������᷵�ش������鿴�������͵�����, ���ݸ����Ĵ���
        //           ���͵���Ӧ�ʼ��������İ�����վ�ϲ鿴����ʧ��ԭ��
        //
        //    PS_02: ����ʧ�ܵ�ԭ��ͨ��Ϊ���¼���, ��ϸ������:
        //           (1) ����û�п��� SMTP ����;
        //           (2) �����������, ����ĳЩ���俪���˶�������;
        //           (3) ���������Ҫ�����Ҫʹ�� SSL ��ȫ����;
        //           (4) �������Ƶ��������ԭ��, ���ʼ��������ܾ�����;
        //           (5) ������ϼ��㶼ȷ������, ���ʼ���������վ���Ұ�����
        //
				//�����쳣��550 Mailbox not found or access denied.
				//�ռ��˵�����ע���˻���qq�˺Ų�������

        //    PS_03: ��ϸ��log, ���濴log, ����log, ����ԭ����log��˵����
		//�������ӣ��˺�Ϊ�����ˣ�����Ϊ��Ȩ��
		transport.connect("hxq1137793660@163.com","vtjhnlvzciqtxxxc");
		//6.�����ʼ����������е��ռ���ַ��message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
		 transport.sendMessage(message, message.getAllRecipients());
		 //7.�ر�����
		 transport.close();
		
	}
	
	
	
		/**
	     * ����һ��ֻ�����ı��ļ��ʼ�
	     *
	     * @param session �ͷ����������ĻỰ
	     * @param sendMail ����������
	     * @param receiveMail �ռ�������
	     * @return MimeMessage
	     * @throws Exception
	     */
		
		//�ʼ�������Ϊmime
		public static MimeMessage creatMimeMessage(Session session,String sendMail, String receiveMail) throws Exception{
			//1.����һ���ʼ�,��Ҫ��һ��session����
			MimeMessage message=new MimeMessage(session);
			
			//2.from�������ˣ��ǳ��й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸��ǳƣ�
			//������Ҫ��һ��address����address�ǽӿڣ�������ʵ����
			//�����������������˺ţ����������֣��ַ�����
			message.setFrom(new InternetAddress(sendMail, "����������", "UTF-8"));
			
			//3.�����ռ��ˣ����������ͣ�TO����ͨ�ռ��ˣ�CC�������ˣ�BCC��������
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "��ͨ�ռ���", "UTF-8"));
			//message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(receiveMail, "��ͨ�ռ���", "UTF-8"));
			//message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(receiveMail, "��ͨ�ռ���", "UTF-8"));
			
			//4.Subject���ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
			message.setSubject("���Ǳ���","UTF-8");

			//5.Content���ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
			message.setContent("z��������.....","text/html;charset=utf-8");
			
			//6.���÷���ʱ��
			message.setSentDate(new Date());
			
			//7.��������
			message.saveChanges();
			return message;
		}
		
	
}

