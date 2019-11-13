package com.zbcm.office.doc4j;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.math.BigInteger;
/**
 *  @title TitleTest
 *  @Description 本文使用编程式的方式去实现一个段落文本的各种样式cao作，跟前篇文章中是同样的道理，
 *  我们一样先采用草稿的方式去观察这种多样式段落的xml结构，再将这种xml结构转换为使用Java对象的方式去处理，
 *  关于本段落的文本所有涉及到的样式有：字体、大小、颜色、居中、粗体、斜体、删除线（中线）、下划线（波浪线）
 *  @author zbcn8
 *  @Date 2019/11/8 16:09
 */
public class TitleTest {


	ObjectFactory factory = Context.getWmlObjectFactory();



	public static void main(String[] args) throws Exception {
		new TitleTest();
	}


	public TitleTest() throws Exception{

		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\多样式段落.docx";

		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();

		MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

		mainDocumentPart.addObject(createTitle());

		wordPackage.save(new File(outPath));

	}



	public P createTitle(){

		RPr rpr = factory.createRPr();

		RFonts font = new RFonts();

		//设置字体

		font.setAscii("宋体");

		font.setEastAsia("宋体");//经测试发现这个设置生效

		rpr.setRFonts(font);

		//设置颜色
		Color color = new Color();

		color.setVal("ABCDEF");

		rpr.setColor(color);

		//设置字体大小

		HpsMeasure fontSize = new HpsMeasure();

		fontSize.setVal(new BigInteger("48"));

		rpr.setSzCs(fontSize);

		rpr.setSz(fontSize);

		//设置粗体

		BooleanDefaultTrue bold = factory.createBooleanDefaultTrue();

		bold.setVal(Boolean.TRUE);

		rpr.setB(bold);

		//设置斜体

		BooleanDefaultTrue ltalic = new BooleanDefaultTrue();

		rpr.setI(ltalic);

		//设置删除线

		BooleanDefaultTrue deleteLine = new BooleanDefaultTrue();

		deleteLine.setVal(Boolean.TRUE);

		rpr.setStrike(deleteLine);

		//设置下划线

		U u = factory.createU();

		u.setVal(UnderlineEnumeration.SINGLE);

		u.setVal(UnderlineEnumeration.DOUBLE);//双下划线

		u.setVal(UnderlineEnumeration.DASH);//虚线

		u.setVal(UnderlineEnumeration.WAVE);//波浪线

		rpr.setU(u);

		//设置显示文本

		Text text = factory.createText();

		text.setValue("雪地里走");

		R r = factory.createR();

		r.getContent().add(text);

		r.setRPr(rpr);

		P p = factory.createP();

		//设置段落居中

		PPr ppr = new PPr();

		Jc jc = new Jc();

		jc.setVal(JcEnumeration.LEFT);

		jc.setVal(JcEnumeration.RIGHT);

		jc.setVal(JcEnumeration.CENTER);

		ppr.setJc(jc);

		p.setPPr(ppr);
		p.getContent().add(r);
		return p;

	}
}
