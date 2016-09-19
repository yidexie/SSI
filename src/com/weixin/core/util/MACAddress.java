package com.weixin.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 通过cmd获取网卡mac地址，获取第一个有ip地址的网卡
 */
public class MACAddress {
	public MACAddress() {
	}

	public static String getMACAddress() {
		String addressStr = "";// MAC网卡地址
		try {
			InetAddress address = InetAddress.getLocalHost();// 取得本地Ip地址
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null) {
				byte[] mac = ni.getHardwareAddress();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						addressStr = addressStr
								+ String.format("%02X%s", mac[i],
										(i < mac.length - 1) ? "-" : "");// 格式化输出
					}
				} else {
					System.out
							.println("InetAddress doesn't exist or is not accessible.");
				}
			} else {
				System.out
						.println("Network Interface for the specified address is not found.");
			}
		} catch (UnknownHostException ex) {
			// ex.printStackTrace();
			System.out.println(ex);
		} catch (SocketException ex1) {
			System.out.println(ex1);
			// ex1.printStackTrace();
		}
		return addressStr.equals("") ? getMACAddress2() : addressStr;
	}

	public static String getMACAddress2() {
		String address = "";
		String ipaddress = "";
		String os = System.getProperty("os.name");
		BufferedReader br = null;
		if (os != null && os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				br = new BufferedReader(new InputStreamReader(p
						.getInputStream()));
				String line;

				OK: while ((line = br.readLine()) != null) {
					OUT: if (line.indexOf("Ethernet adapter") >= 0) {
						// 初始化
						address = "";
						ipaddress = "";

						while ((line = br.readLine()) != null) {
							if (line.indexOf("Ethernet adapter") > 0)
								break OUT;

							if (line.indexOf("Physical Address") > 0) {
								int index = line.indexOf(":");
								address = line.substring(index + 1).trim();
							}

							if (line.indexOf("IP Address") > 0) {
								int index = line.indexOf(":");
								ipaddress = line.substring(index + 1).trim();
							}
							// 蓝牙可能被当作一个网卡，但是没有ip
							if (!address.equals("") && !ipaddress.equals("")) {
								// System.out.println("获取成功");
								break OK;
							}
						}
					}

				}

			} catch (IOException e) {
			} finally {
				try {
					br.close();
				} catch (Exception e) {
				}
			}
		}
		return address;
	}

	/**
	 * Return available free disk space for a directory.
	 * 
	 * @[EMAIL PROTECTED] dirName name of the directory
	 * @[EMAIL PROTECTED] free disk space in bytes or -1 if unknown
	 */

	public static long getFreeDiskSpace(String dirName) {
		if (dirName.length() > 2)
			dirName = dirName.substring(0, 2);
		try {
			// guess correct 'dir' command by looking at the
			// operating system name
			String os = System.getProperty("os.name");
			String command;
			if (os.toLowerCase().indexOf("windows") != -1) {
				command = "cmd.exe /c dir " + dirName;
			} else {
				command = "command.com /c dir " + dirName;
			}
			// run the dir command on the argument directory name
			Runtime runtime = Runtime.getRuntime();
			Process process = null;
			process = runtime.exec(command);
			if (process == null) {
				return -1;
			}
			// read the output of the dir command
			// only the last line is of interest
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			String freeSpace = null;
			while ((line = in.readLine()) != null) {
				freeSpace = line;
			}
			if (freeSpace == null) {
				return -1;
			}
			process.destroy();

			// remove dots & commas & leading and trailing whitespace
			freeSpace = freeSpace.trim();
			freeSpace = freeSpace.replaceAll("\\.", "");
			freeSpace = freeSpace.replaceAll(",", "");
			String[] items = freeSpace.split(" ");
			// the first valid numeric value in items after(!) index 0
			// is probably the free disk space
			int index = 1;
			while (index < items.length) {
				try {
					long bytes = Long.parseLong(items[index++]);
					return bytes;
				} catch (NumberFormatException nfe) {
				}
			}
			return -1;
		} catch (Exception exception) {
			return -1;
		}
	}
}
