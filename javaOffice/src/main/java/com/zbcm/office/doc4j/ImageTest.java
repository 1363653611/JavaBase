package com.zbcm.office.doc4j;

import org.apache.commons.io.FileUtils;
import org.docx4j.dml.wordprocessingDrawing.Anchor;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.P;
import org.docx4j.wml.Text;

import java.io.File;

public class ImageTest {
	public static void main(String[] args) throws Exception {

		String imagePath = System.getProperty("user.dir") + "\\javaOffice\\template\\images\\desktop.jpg";

		File image = new File(imagePath);

		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();

		//插入图片1---图片格式为嵌入型

		P p1 = newImageInline(wordPackage, FileUtils.readFileToByteArray(image), "111111111", "22222",

				1001, 1002 , "这是图1，desktop.jsp，看图片的嵌入方式");

		wordPackage.getMainDocumentPart().addObject(p1);

		//插入图片2---图片格式为嵌入型

		P p2 = newImageInline(wordPackage, FileUtils.readFileToByteArray(new File(image.getParentFile() ,

				"name.png")), "", "" , 2001, 2002 , "这是图2，name.png，看图片的嵌入方式");

		wordPackage.getMainDocumentPart().addObject(p2);

		//插入图片3---图片格式为衬于文字下方

//		P p3 = newImageAnchor(wordPackage, FileUtils.readFileToByteArray(new File(image.getParentFile() ,
//
//				"name.png")), "", "" , 2001, 2002 , "这是图3，name.png，看图片的嵌入方式");
//
//		wordPackage.getMainDocumentPart().addObject(p3);



		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\编程式图片.docx";

		wordPackage.save(new File(outPath));

	}



	/**

	 * 摘自github源码示例代码中的 ImageAdd.java

	 */

	public static org.docx4j.wml.P newImageInline(

			WordprocessingMLPackage wordMLPackage, byte[] bytes,

			String filenameHint, String altText, int id1, int id2 , String text)

			throws Exception {



		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);



		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, false);



		// Now add the inline in w:p/w:r/w:drawing

		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();

		org.docx4j.wml.P p = factory.createP();

		org.docx4j.wml.R run = factory.createR();

		if(text != null){

			Text txt = factory.createText();

			txt.setValue(text);

			run.getContent().add(txt);

		}

		p.getContent().add(run);

		org.docx4j.wml.Drawing drawing = factory.createDrawing();

		run.getContent().add(drawing);

		drawing.getAnchorOrInline().add(inline);

		return p;



	}



	/**

	 * Create image, without specifying width

	 */

	public static org.docx4j.wml.P newImageAnchor(WordprocessingMLPackage wordMLPackage,

	                                              byte[] bytes,

	                                              String filenameHint, String altText,

	                                              int id1, int id2, String text) throws Exception {



		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

		//Anchor inline = imagePart.createImageAnchor(filenameHint, altText, id1, id2, false, 100, 200);

		// Now add the inline in w:p/w:r/w:drawing

		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();

		org.docx4j.wml.P  p = factory.createP();

		org.docx4j.wml.R  run = factory.createR();

		if(text != null){

			Text txt = factory.createText();

			txt.setValue(text);

			run.getContent().add(txt);

		}

		p.getContent().add(run);

		org.docx4j.wml.Drawing drawing = factory.createDrawing();

		run.getContent().add(drawing);

		//drawing.getAnchorOrInline().add(inline);



		return p;



	}
}
