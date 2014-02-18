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

import java.net.URL;

import org.btc4j.daemon.BtcDaemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BtcDaemonListener {
	@Autowired
	private URL daemonUrl = null;
	
	public String invokeJsonRpc(BtcRequestMessage request) {
		try {
			return getDaemon(request.getAccount(), request.getPassword()).jsonInvoke(request.getBody());
		} catch (Throwable t) {
			return String.valueOf(t);
		}
	}
	
	public void addMultiSignatureAddress(BtcRequestMessage request) {
		System.out.println("addMultiSignatureAddress message: " + request);
	}
	
	public String help(BtcRequestMessage request) {
		System.out.println("help message: " + request);
		return "help reply";
	}
	
	public String stop(BtcRequestMessage request) {
		System.out.println("stop message: " + request);
		return request.getBody();
	}
	
	private synchronized BtcDaemon getDaemon(String account, String password) {
		return new BtcDaemon(daemonUrl, account, password);
	}
}
