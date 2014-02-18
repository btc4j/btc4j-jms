/*
 The MIT License (MIT)
 
 Copyright (c) 2013, 2014 by Guillermo Gonzalez, btc4j.org

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package org.btc4j.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;

public class BtcDaemonCaller {
	private JmsTemplate jmsTemplate = null;
	private String account = "";
	private String password = "";

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String sendReceive(String destination) {
		return sendReceive(destination, "");
	}
	
	public String sendReceive(final String destination, final String payload) {
		return jmsTemplate.execute(new SessionCallback<String>() {
			@Override
			public String doInJms(Session session) throws JMSException {
				final TemporaryQueue replyQueue = session.createTemporaryQueue();
				jmsTemplate.send(destination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage message = session.createTextMessage();
						message.setJMSReplyTo(replyQueue);
						message.setText(payload);
						message.setStringProperty("btcapi:account", account);
						message.setStringProperty("btcapi:password", password);
						return message;
					}
				});
				return String.valueOf(jmsTemplate.receiveAndConvert(replyQueue));
			}
		});
	} 
	
	public void send(String destination) {
		send(destination, "");
	}
	
	public void send(String destination, final String payload) {
		jmsTemplate.convertAndSend(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(payload);
				message.setStringProperty("btcapi:account", account);
				message.setStringProperty("btcapi:password", password);
				return message;
			}
		});
	}
}
