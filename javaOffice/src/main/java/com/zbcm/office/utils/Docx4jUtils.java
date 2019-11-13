package com.zbcm.office.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.ConversionHTMLStyleElementHandler;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.HTMLConversionContext;
import org.docx4j.convert.out.html.HtmlCssHelper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * docx4j 操作 .doc 工具类
 */
@Slf4j
public class Docx4jUtils {

	private static String docxPath = "D:\\yonyou\\program\\SSSFM\\closeDevDoc\\30.产品设计\\财政社保产品设计\\产品设计文档\\统计分析和辅助决策\\风险评估与精算平衡\\社保_功能前端\\报告\\例\\精算监管年报模板.docx";

	/**
	 * 获取文档可操作对象
	 * @param docxPath 文档路径
	 * @return
	 */
	static WordprocessingMLPackage getWordprocessingMLPackage(String docxPath) {
		WordprocessingMLPackage wordMLPackage = null;
		if(StringUtils.isEmpty(docxPath)) {
			try {
				wordMLPackage =  WordprocessingMLPackage.createPackage();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File file = new File(docxPath);
		if(file.isFile()) {
			try {
				wordMLPackage = WordprocessingMLPackage.load(file);
			} catch (Docx4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wordMLPackage;
	}


	/**
	 * 像文档中追加内容(默认支持中文)
	 * 先清空,在生成,防重复
	 */
	public static void addParagraph() {
		WordprocessingMLPackage wordprocessingMLPackage;
		try {
			//先加载word文档
			wordprocessingMLPackage = WordprocessingMLPackage.load(new File(docxPath));
//            Docx4J.load(new File(docxPath));

			//增加内容
			wordprocessingMLPackage.getMainDocumentPart().addParagraphOfText("你好!");
			wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "这是标题!");
			wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle", " 这是二级标题!");

			wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subject", "试一试");
			//保存文档
			wordprocessingMLPackage.save(new File(docxPath));
		} catch (Docx4JException e) {
			log.error("addParagraph to docx error: Docx4JException", e);
		}
	}

	/**
	 * 读取word文件，这里没有区分 word中的样式格式
	 */
	public static void readParagraph(String path) {
		try {
			WordprocessingMLPackage wordprocessingMLPackage = getWordprocessingMLPackage(path);

			String contentType = wordprocessingMLPackage.getContentType();
			log.info("contentType:"+contentType);
			MainDocumentPart part = wordprocessingMLPackage.getMainDocumentPart();
			List<Object> list = part.getContent();
			System.out.println("content -> body -> "+part.getContents().getBody().toString());
			for(Object o :list) {
				log.info("info:"+o);
			}
		}catch(Exception e) {

		}
	}


	/**
	 * 转xls,在转html
	 */
	public static void wordToHtml() {
		boolean nestLists = true;
		boolean save = true;
		WordprocessingMLPackage wordprocessingMLPackage = getWordprocessingMLPackage(docxPath);

		HTMLSettings html = Docx4J.createHTMLSettings();
		//设置图片的目录地址
		html.setImageDirPath(docxPath + "_files");
		html.setImageTargetUri(docxPath.substring(docxPath.lastIndexOf("/") + 1 ) + "_files");
		html.setWmlPackage(wordprocessingMLPackage);
		String userCSS = null;
		if (nestLists) {
			userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  table, caption, tbody, tfoot, thead, tr, th, td "
					+ "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";
		} else {
			userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td "
					+ "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";
		}

		html.setUserCSS(userCSS);
		OutputStream os = null;
		try {
			if (save) {
				os = new FileOutputStream(docxPath + ".html");
			} else {
				os = new ByteArrayOutputStream();
			}
			//设置输出
			Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

			Docx4J.toHTML(html, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
		}catch(Exception e) {

		}
		if (save) {
			System.out.println("Saved: " + docxPath + ".html ");
		} else {
			System.out.println(((ByteArrayOutputStream) os).toString());
		}
		if (wordprocessingMLPackage.getMainDocumentPart().getFontTablePart() != null) {
			wordprocessingMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
		}
		html = null;
		wordprocessingMLPackage = null;
	}

	public static void main(String[] args) {
		readParagraph(docxPath);
	}
}
