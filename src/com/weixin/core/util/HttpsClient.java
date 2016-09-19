package com.weixin.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import com.weixin.core.util.StringUtil;

public class HttpsClient {
	private static X509TrustManager tm = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	@SuppressWarnings("deprecation")
	public static HttpClient getInstance() throws KeyManagementException,
			NoSuchAlgorithmException {
		HttpClient client = new DefaultHttpClient();
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = client.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", ssf, 443));
		client = new DefaultHttpClient(ccm, client.getParams());
		return client;
	}

	public static String getUrlContent(String url) {
		String result = "";
		try {
			HttpClient httpsClient = HttpsClient.getInstance();
			httpsClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 20000);
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpsClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity
					.getContent(), "utf-8"));
			StringBuffer content = new StringBuffer();
			for (String line; (line = br.readLine()) != null;) {
				content.append(line + "\r\n");
			}
			result = content.toString();
		} catch (Exception e) {
			result = e.toString();
			e.printStackTrace();
		}
		return result;
	}

	public static String getUrlContent(String url, String proxyIp, int proxyPort) {
		String result = "";
		try {
			HttpClient httpsClient = HttpsClient.getInstance();
			if (!StringUtil.isNullOrEmpty(proxyIp) && proxyPort > 0) {
				HttpHost proxy = new HttpHost(proxyIp, proxyPort);
				httpsClient.getParams().setParameter(
						ConnRoutePNames.DEFAULT_PROXY, proxy);
			}
			httpsClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 20000);
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpsClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity
					.getContent(), "utf-8"));
			StringBuffer content = new StringBuffer();
			for (String line; (line = br.readLine()) != null;) {
				content.append(line + "\r\n");
			}
			result = content.toString();
		} catch (Exception e) {
			result = e.toString();
			e.printStackTrace();
		}
		return result;
	}
}