package com.zbcm.office.doc4j;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.File;
import java.util.HashMap;

public class IncomeTemplateTest {
	public static void main(String[] args) throws Exception {

		String templatePath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\收入证明_template.docx";

		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\收入证明.docx";

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(templatePath));

		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

		HashMap<String, String> mappings = new HashMap<String, String>();

		mappings.put("name", "马参军");

		mappings.put("years", "5");

		mappings.put("post", "攻城狮");

		mappings.put("money", "25,000.00");

		mappings.put("moneyChinese", "二万五年里");

		mappings.put("address", "天宫一号天宫一号天宫一号天宫一号");

		mappings.put("telephone", "188188188188");

		mappings.put("year", "2018");

		mappings.put("month", "09");

		mappings.put("date", "11");

		documentPart.variableReplace(mappings);

		Docx4J.save(wordMLPackage, new File(outPath));

	}
}
