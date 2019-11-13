package com.zbcm.office.doc4j;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.util.List;

/**
 * ，然后使用解压缩软件打开这个docx文档，找到document.xml，打开后发现它是有这么一堆的xml标签构成的
 */
public class HelloWordTest {

	public static void main(String[] args) throws Exception {

		//创建一个文档

		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();

		//获得这个文档的document跟标签对象

		MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

		//获取到body标签，并向body中添加子级元素

		List<Object> body = mainDocumentPart.getContent();

		//工厂类，可创建各种标签对象

		ObjectFactory factory = Context.getWmlObjectFactory();

		R r = factory.createR();

		RPr rpr = factory.createRPr();

		RFonts rFont = factory.createRFonts();

		STHint sthInt = STHint.EAST_ASIA;

		rFont.setHint(sthInt);

		rpr.setRFonts(rFont);

		r.setRPr(rpr);

		Text text = factory.createText();

		text.setValue("hello，陈dd");

		r.getContent().add(text);

		P p = factory.createP();

		p.getContent().add(r);

		body.add(p);

		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\helloworld.docx";

		wordPackage.save(new File(outPath));

	}
}
