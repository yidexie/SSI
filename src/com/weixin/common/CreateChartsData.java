package com.weixin.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class CreateChartsData {
	private static Logger logger = Logger.getLogger(CreateChartsData.class);
	private static String filepath;

	/**
	 * 集合参数中以 名字&数量 的形式，饼状图
	 * 
	 * @param datas
	 */
	public static void parsePieData(List datas, String filename) {
		// cleanFiles(filename);
		filepath = new File(new File(CreateChartsData.class.getResource("/")
				.toString().replace("file:/", "").replace("%20", " "))
				.getParent()).getParent()
				+ "/home/temp/" + filename + ".txt";
		logger.info("饼状图数据文件指向地址为：" + filepath);
		File f = null;
		OutputStreamWriter writer = null;
		try {
			f = new File(filepath);
			f.createNewFile();
			writer = new OutputStreamWriter(new FileOutputStream(f, true),
					"UTF-8");
			for (int i = 0; i < datas.size(); i++) {
				if (i == 0)
					writer.append(datas.get(i).toString().replace("&", ";")
							+ ";true;url:http://www.baidu.com;" + "\r\n");
				else
					writer.append(datas.get(i).toString().replace("&", ";")
							+ "\r\n");
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 集合参数中以 名字&数量 的形式，柱状图
	 * 
	 * @param datas
	 */
	public static void parseColumnData(List datas, String filename) {
		// cleanFiles(filename);
		filepath = new File(new File(CreateChartsData.class.getResource("/")
				.toString().replace("file:/", "").replace("%20", " "))
				.getParent()).getParent()
				+ "/home/temp/" + filename + ".xml";
		logger.info("柱状图数据文件指向地址为：" + filepath);
		File f = new File(filepath);

		Document document = DocumentHelper.createDocument();
		Element chartElement = document.addElement("chart");
		Element seriesElement = chartElement.addElement("series");
		Element graphsElement = chartElement.addElement("graphs");
		for (int i = 0; i < datas.size(); i++) {
			String name = new String(datas.get(i).toString().split("&")[0]);
			String value = new String(datas.get(i).toString().split("&")[1]);
			Element svalueElement = seriesElement.addElement("value");
			svalueElement.addAttribute("xid", String.valueOf(i));

			Element graphElement = graphsElement.addElement("graph");
			graphElement.addAttribute("gid", String.valueOf(i));
			graphElement.addAttribute("title", name);
			Element gvalueElement = graphElement.addElement("value");
			gvalueElement.addAttribute("xid", String.valueOf(i));
			// gvalueElement.addAttribute("url", "http://www.baidu.com");
			gvalueElement.setText(value);
		}

		try {
			XMLWriter output = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(f, true), "UTF-8"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 集合参数中以 名字&数量 的形式，柱状图，一个纵坐标有多个柱子
	 * 
	 * @param datas
	 */
	public static void parseColumnData2(List datas, String filename, List zone) {
		filepath = new File(new File(CreateChartsData.class.getResource("/")
				.toString().replace("file:/", "").replace("%20", " "))
				.getParent()).getParent()
				+ "/home/temp/" + filename + ".xml";
		logger.info("柱状图数据文件指向地址为：" + filepath);
		File f = new File(filepath);
		if (f.exists())
			f.delete();

		Document document = DocumentHelper.createDocument();
		Element chartElement = document.addElement("chart");
		Element seriesElement = chartElement.addElement("series");
		Element graphsElement = chartElement.addElement("graphs");
		for (int j = 0; j < datas.size(); j++) {
			Element svalueElement = seriesElement.addElement("value");
			svalueElement.addAttribute("xid", String.valueOf(j));
			svalueElement.setText(datas.get(j).toString().split("&")[0]);
		}

		for (int i = 0; i < zone.size(); i++) {
			Element graphElement = graphsElement.addElement("graph");
			graphElement.addAttribute("gid", String.valueOf(i));
			graphElement.addAttribute("title", zone.get(i).toString());

			for (int j = 0; j < datas.size(); j++) {
				Element gvalueElement = graphElement.addElement("value");
				gvalueElement.addAttribute("xid", String.valueOf(j));
				gvalueElement
						.setText(datas.get(j).toString().split("&")[i + 1]);
			}
		}
		try {
			XMLWriter output = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(f, true), "UTF-8"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 集合参数中以 名字&数量 的形式，柱状图带链接
	 * 
	 * @param datas
	 */
	public static void parseColumnData3(List datas, String filename, String url) {
		// cleanFiles(filename);
		filepath = new File(new File(CreateChartsData.class.getResource("/")
				.toString().replace("file:/", "").replace("%20", " "))
				.getParent()).getParent()
				+ "/home/temp/" + filename + ".xml";
		logger.info("柱状图数据文件指向地址为：" + filepath);
		File f = new File(filepath);

		Document document = DocumentHelper.createDocument();
		Element chartElement = document.addElement("chart");
		Element seriesElement = chartElement.addElement("series");
		Element graphsElement = chartElement.addElement("graphs");
		for (int i = 0; i < datas.size(); i++) {
			String name = new String(datas.get(i).toString().split("&")[0]);
			String value = new String(datas.get(i).toString().split("&")[1]);
			String para = new String(datas.get(i).toString().split("&")[2]);
			Element svalueElement = seriesElement.addElement("value");
			svalueElement.addAttribute("xid", String.valueOf(i));

			Element graphElement = graphsElement.addElement("graph");
			graphElement.addAttribute("gid", String.valueOf(i));
			graphElement.addAttribute("title", name);
			Element gvalueElement = graphElement.addElement("value");
			gvalueElement.addAttribute("xid", String.valueOf(i));
			gvalueElement.addAttribute("url", url + para);
			gvalueElement.setText(value);
		}

		try {
			XMLWriter output = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(f, true), "UTF-8"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 集合参数中以 名字&数量 的形式，折线图，趋势图
	 * 
	 * @param datas
	 */
	public static void parseLineData(List datas, String filename, List zone) {
		// cleanFiles(filename);
		filepath = new File(new File(CreateChartsData.class.getResource("/")
				.toString().replace("file:/", "").replace("%20", " "))
				.getParent()).getParent()
				+ "/home/temp/" + filename + ".xml";
		logger.info("趋势图数据文件指向地址为：" + filepath);
		File f = new File(filepath);

		Document document = DocumentHelper.createDocument();
		Element chartElement = document.addElement("chart");
		Element seriesElement = chartElement.addElement("series");
		Element graphsElement = chartElement.addElement("graphs");
		@SuppressWarnings("unused")
		String tempStr = null;
		for (int i = 0; i < zone.size(); i++) {// 先加入X轴标量
			Element svalueElement = seriesElement.addElement("value");
			svalueElement.addAttribute("xid", String.valueOf(i));
			svalueElement.setText(zone.get(i).toString());
		}
		Element graphElement = null;
		int k = 0;
		String tvalue = null;
		for (int j = 0; (k != zone.size() || j != datas.size())
				&& datas.size() > 0;) {
			String name = null;
			String value = null;
			String index = null;
			if (j != datas.size()) {
				name = new String(datas.get(j).toString().split("&")[0]);
				value = new String(datas.get(j).toString().split("&")[1]);
				index = new String(datas.get(j).toString().split("&")[2]);
			}
			if (j == 0 || k == zone.size()) {
				// 若不是同一个对象，则需要新建一个标签
				graphElement = graphsElement.addElement("graph");
				graphElement.addAttribute("gid", String.valueOf(j));
				graphElement.addAttribute("title", name);
				graphElement.addAttribute("line_width", "2");
				graphElement.addAttribute("bullet", "round");
				graphElement.addAttribute("balloon_text",
						"{title}    ({value})");
				k = zone.indexOf(index);
			}

			if (zone.get(k).equals(index)) {
				Element gvalueElement = graphElement.addElement("value");
				gvalueElement.addAttribute("xid", String.valueOf(k));
				gvalueElement.setText(value);
				tvalue = value;
				j++;
			} else {
				Element gvalueElement = graphElement.addElement("value");
				gvalueElement.addAttribute("xid", String.valueOf(k));
				gvalueElement.setText(tvalue);
			}
			k++;
		}
		try {
			XMLWriter output = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(f, true), "UTF-8"));
			output.write(document);
			output.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void cleanFiles(String filename) {
		filepath = new File(new File(CreateChartsData.class.getResource("/")
				.toString().replace("file:/", "").replace("%20", " "))
				.getParent()).getParent()
				+ "/home/temp/";
		File delf = new File(filepath);
		File fs[] = delf.listFiles();
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].getName().startsWith(filename.split("_")[0])) {
				fs[i].delete();
			}
		}
	}

	public static void main(String[] args) {
		String x = CreateChartsData.class.getResource("").toString();
		x = ClassLoader.getSystemResource("").toString().replace("file:/", "");
		x = new File(new File(x).getParent()).getParent();
		System.out.println(x);
	}
}
