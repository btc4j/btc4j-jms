/*
 The MIT License (MIT)
 
 Copyright (c) 2013, 2014 by ggbusto@gmx.com

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

import org.btc4j.core.BtcUtil;

public class BtcRequestMessage {
	public static final String BTCAPI_ACCOUNT = "btcapi:account";
	public static final String BTCAPI_PASSWORD = "btcapi:password";
	private String account = "";
	private String password = "";
	private String body = "";
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = BtcUtil.notNull(account);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BtcUtil.notNull(password);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = BtcUtil.notNull(body);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BtcMessage [account=");
		builder.append(account);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}
}
