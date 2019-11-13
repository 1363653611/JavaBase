package com.zbcm.office.doc4j;

import org.docx4j.Docx4J;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.io.FileOutputStream;

/**
 *  @title ConvertTest
 *  @Description 文档格式转换
 *  @author zbcn8
 *  @Date 2019/11/11 10:45
 */
public class ConvertTest {
	static String templatePath = System.getProperty("user.dir") + "\\javaOffice\\template\\我的增量补丁整理软件.docx";



	public static void main(String[] args) throws Exception {

		//将docx文件转换为pdf文档

		convertDocx2Pdf();

		//将docx文件转换为html文档

		convertDocx2Html();

		//将docx转换为doc文档

		convertDocx2Doc();

	}



	private static void convertDocx2Doc() throws Exception {

		//将docx转换为符合doc格式规范的xml文档，再由xml更改后缀名为doc的方式达到docx转换doc格式的目的

		File templateFile = new File(templatePath);

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);

		File outFile = new File(templateFile.getParent() + "/out/" , "我的增量补丁整理软件.xml");

		Docx4J.save(wordMLPackage, outFile, Docx4J.FLAG_SAVE_FLAT_XML);//FLAG_SAVE_ZIP_FILE

		outFile.renameTo(new File(outFile.getParentFile() , "我的增量补丁整理软件.doc"));

	}



	private static void convertDocx2Html() throws Exception {



		File templateFile = new File(templatePath);

		String name = "我的增量补丁整理软件.html";

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);

		HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

		String folder = templateFile.getParent() + "/out/";

		htmlSettings.setImageDirPath(folder + name + "_files");//生成的html文件与图片文件夹之类的放置同一个目录下

		htmlSettings.setImageTargetUri(name + "_files");

		htmlSettings.setWmlPackage(wordMLPackage);

		File outFile = new File(folder , name);

		Docx4J.toHTML(htmlSettings, new FileOutputStream(outFile), Docx4J.FLAG_NONE);

	}



	//转换为Pdf文件

	private static void convertDocx2Pdf() throws Exception {



		File templateFile = new File(templatePath);

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateFile);

		File outFile = new File(templateFile.getParent() + "/out/" , "我的增量补丁整理软件.pdf");

		Docx4J.toPDF(wordMLPackage, new FileOutputStream(outFile));

	}
}
