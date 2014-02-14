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
import javax.jms.Session;
import javax.jms.TemporaryQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.stereotype.Component;

@Component
public class BtcDaemonCaller {
	@Autowired
	private JmsTemplate jmsTemplate = null;
	// TODO wire account/password from xml

	public String sendReceive(String destinationName, String account, String password) {
		return sendReceive(destinationName, account, password, "");
	}
	
	public String sendReceive(final String destinationName, final String account, final String password, final String payload) {
		return jmsTemplate.execute(new SessionCallback<String>() {
			@Override
			public String doInJms(Session session) throws JMSException {
				TemporaryQueue replyQueue = session.createTemporaryQueue();
				jmsTemplate.convertAndSend(destinationName, new BtcRequestMessage(account, password, payload, replyQueue));
				return String.valueOf(jmsTemplate.receiveAndConvert(replyQueue));
			}
		});
	} 
	
	public void send(String destinationName, String account, String password) {
		send(destinationName, account, password, "");
	}
	
	public void send(String destinationName, String account, String password, String payload) {
		jmsTemplate.convertAndSend(destinationName, new BtcRequestMessage(account, password, payload));
	}
}
