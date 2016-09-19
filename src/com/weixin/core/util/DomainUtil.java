package com.weixin.core.util;

/**
 * 基本工具类
 */
public class DomainUtil {

	// 包含全部的cn域名后缀
	static String[] xCN = { ".com.cn", ".net.cn", ".gov.cn", ".edu.cn",
			".org.cn", ".mil.cn", ".ac.cn", ".bj.cn", ".sh.cn", ".tj.cn",
			".cq.cn", ".he.cn", ".sx.cn", ".nm.cn", ".ln.cn", ".jl.cn",
			".hl.cn", ".js.cn", ".zj.cn", ".ah.cn", ".fj.cn", ".jx.cn",
			".sd.cn", ".ha.cn", ".hb.cn", ".hn.cn", ".gd.cn", ".gx.cn",
			".hi.cn", ".sc.cn", ".gz.cn", ".yn.cn", ".xz.cn", ".sn.cn",
			".gs.cn", ".qh.cn", ".nx.cn", ".xj.cn", ".tw.cn", ".hk.cn",
			".mo.cn" };

	public static String getHost1(String host) {
		host = host.trim().toLowerCase();// 格式化
		host = getHost2(host);// 先获取二级域名
		String domain1 = "";
		if (host.endsWith(".cn")) {
			// 判断cn分类域名以及区域域名
			for (int i = 0; i < xCN.length; i++) {
				if (host.endsWith(xCN[i])) {
					host = host.substring(0, host.length() - xCN[i].length());
					String[] _s = host.split("\\.");
					if (_s.length > 0) {
						domain1 = _s[_s.length - 1] + xCN[i];
					}
					return domain1;
				}
			}
			// else if(host.endsWith(".cn")){
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".cn";
			// }
		} else if (host.endsWith(".com")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			domain1 = _s[_s.length - 1] + ".com";
		}

		else if (host.endsWith(".net")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".net";
		}

		else if (host.endsWith(".org")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".org";
		}

		else if (host.endsWith(".gov")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".gov";
		}

		else if (host.endsWith(".edu")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".edu";
		}

		else if (host.endsWith(".biz")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".biz";
		}

		else if (host.endsWith(".tv")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".tv";
		}

		else if (host.endsWith(".cc")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".cc";
		}

		else if (host.endsWith(".be")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".be";
		}

		else if (host.endsWith(".info")) {
			host = host.substring(0, host.length() - 5);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".info";
		}

		else if (host.endsWith(".name")) {
			host = host.substring(0, host.length() - 5);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".name";
		}

		else if (host.endsWith(".co.uk")) {
			host = host.substring(0, host.length() - 6);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".co.uk";
		}

		else if (host.endsWith(".me.uk")) {
			host = host.substring(0, host.length() - 6);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".me.uk";
		}

		else if (host.endsWith(".org.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".org.uk";
		}

		else if (host.endsWith(".ltd.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".ltd.uk";
		}

		else if (host.endsWith(".plc.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".plc.uk";
		} else if (host.endsWith(".com.tw")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".com.tw";
		} else if (host.endsWith(".au")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".au";
		} else if (host.endsWith(".ca")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".ca";
		}
		return domain1;
	}

	// 获取二级域名
	public static String getHost2(String host) {
		if (host.startsWith("http://"))
			host = host.substring(7);
		else if (host.startsWith("https://"))
			host = host.substring(8);
		int n = host.indexOf("/");
		if (n != -1)
			host = host.substring(0, n);

		int m = host.indexOf(":");
		if (m > 0)
			host = host.substring(0, m);

		return host;
	}

	public static String getHost3(String host) {
		if (host.startsWith("http://"))
			host = host.substring(7);
		else if (host.startsWith("https://"))
			host = host.substring(8);

		return host;
	}

	// 判断一级域名或二级域名
	public static int getDomianType(String host) {
		int DomianType = 0;
		host = getHost2(host);
		String host1 = getHost1(host);

		if (host.equals(host1))
			DomianType = 1;
		else {
			if (host.indexOf(host1) > 1)
				DomianType = 2;
		}
		return DomianType;
	}

	// 获取域名(返回一级域名或二级域名)
	public static String getHost(String host) {
		host = getHost2(host);
		String host1 = getHost1(host);
		if (host1.equals(""))
			return "";
		if (host.equals(host1))
			return host1;
		else {
			if (host.indexOf(host1) > 1)
				return host;
		}
		return host;
	}

}
