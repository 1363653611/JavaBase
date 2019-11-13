package com.zbcm.office.doc4j;

import org.apache.commons.io.FileUtils;
import org.docx4j.Docx4J;
import org.docx4j.dml.CTNonVisualDrawingProps;
import org.docx4j.dml.wordprocessingDrawing.Anchor;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Drawing;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.util.List;

public class ImageTemplateTest {
	public static void main(String[] args) throws Exception {

		String templatePath = System.getProperty("user.dir") + "\\javaOffice\\template\\模板式图片_template.docx";

		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\模板式图片.docx";

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(templatePath));

		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

		String xPath = "//w:drawing";

		List<Object> list = documentPart.getJAXBNodesViaXPath(xPath, true);

		@SuppressWarnings("unchecked")

		JAXBElement<Drawing> element = (JAXBElement<Drawing>) list.get(0);

		Drawing drawing = element.getValue();



		//获取原图的相关信息，再取创建一个新的图片，用户替换原图

		Anchor anchor = (Anchor) drawing.getAnchorOrInline().get(0);//当前的图片

		Integer posH = anchor.getPositionH().getPosOffset();//原占位图的坐标位置

		Integer posV = anchor.getPositionV().getPosOffset();

		CTNonVisualDrawingProps docPr = anchor.getDocPr();

		int xId = (int) docPr.getId();

		String filenameHint = docPr.getName();

		String altText = docPr.getDescr();

		int yId = (int) anchor.getGraphic().getGraphicData().getPic().getNvPicPr().getCNvPr().getId();


		String imagePath = System.getProperty("user.dir") + "\\javaOffice\\template\\images\\badge.png";

		byte bytes[] = FileUtils.readFileToByteArray(new File(imagePath));

		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);



		Anchor inline = imagePart.createImageAnchor(filenameHint, altText, xId, yId, false, posH, posV);

		drawing.getAnchorOrInline().set(0, inline);

		System.out.println("--------图片已经替换--------");



		Docx4J.save(wordMLPackage, new File(outPath));

	}
}
