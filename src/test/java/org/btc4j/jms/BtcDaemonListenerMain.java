/*
 The MIT License (MIT)
 
 Copyright (c) 2013, 2014 by ggbusto

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

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BtcDaemonListenerMain {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("btc4j-jms-test.xml");
			BtcDaemonCaller caller = (BtcDaemonCaller) ctx.getBean("daemonCaller");
			System.out.println(caller.sendReceive("BTCAPI.JSON_RPC.INVOKE", "{\"jsonrpc\":\"2.0\",\"method\":\"help\",\"params\":[\"getblock\"],\"id\":\"400ec474-5b67-4f82-bb65-4e10ee807d04\"}"));
			ctx.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
