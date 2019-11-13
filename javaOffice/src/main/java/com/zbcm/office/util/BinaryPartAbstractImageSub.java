package com.zbcm.office.util;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
import org.apache.xmlgraphics.image.loader.ImageSize;
import org.docx4j.UnitsOfMeasurement;
import org.docx4j.dml.wordprocessingDrawing.Anchor;
import org.docx4j.model.structure.PageDimensions;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.ExternalTarget;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import sun.plugin2.message.GetNameSpaceMessage;

import javax.xml.bind.JAXBElement;
import java.awt.geom.Dimension2D;
import java.util.List;

public class BinaryPartAbstractImageSub extends BinaryPartAbstractImage {

	final static String namespaces = " xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
			+ "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
			+ "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\"";

	public BinaryPartAbstractImageSub(PartName partName) throws InvalidFormatException {
		super(partName);
	}

	public BinaryPartAbstractImageSub(ExternalTarget externalTarget) {
		super(externalTarget);
	}
	/**
	 * Create a <wp:anchor> element suitable for this image, which can be
	 * linked or embedded in w:p/w:r/w:drawing, specifying height and width.  Note
	 * that you'd ordinarily use one of the methods which don't require
	 * you to specify height (cy).
	 *
	 * @param filenameHint
	 *            Any text, for example the original filename
	 * @param altText
	 *            Like HTML's alt text
	 * @param id1
	 *            An id unique in the document
	 * @param id2
	 *            Another id unique in the document None of these things seem to
	 *            be exposed in Word 2007's user interface, but Word won't open
	 * the document if any of the attributes these go in (except @ desc) aren't
	 *            present!
	 * @param cx    Image width in EMU
	 * @param cy    Image height in EMU
	 * @param link  true if this is to be linked not embedded
	 * @throws Exception
	 */
	public Anchor createImageAnchor(String filenameHint, String altText,
	                                int id1, int id2, long cx, long cy, boolean link,
	                                long posHOffset, long posVOffset) throws Exception {

		// Floating - The drawing object is anchored within the text, but can be absolutely positioned in the
		// document relative to the page.
		if (filenameHint == null) {
			filenameHint = "";
		}
		if (altText == null) {
			altText = "";
		}

		String type;
		if (link) {
			type = "r:link";
		} else {
			type = "r:embed";
		}

		String ml =
				"<wp:anchor distT=\"0\" distB=\"0\" distL=\"114300\" distR=\"114300\" simplePos=\"0\" relativeHeight=\"251658240\" behindDoc=\"1\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\"" + namespaces + ">" //  wp14:anchorId=\"28C768E3\" wp14:editId=\"050FA02A\">"
						+ "<wp:simplePos x=\"0\" y=\"0\"/>"
						+ "<wp:positionH relativeFrom=\"column\"><wp:posOffset>${posHOffset}</wp:posOffset></wp:positionH>"
						+ "<wp:positionV relativeFrom=\"paragraph\"><wp:posOffset>${posVOffset}</wp:posOffset></wp:positionV>"
						+ "<wp:extent cx=\"${cx}\" cy=\"${cy}\"/>"
						+ "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"  // b=4445
						+ "<wp:wrapNone/>"
						+ "<wp:docPr id=\"${id1}\" name=\"${filenameHint}\" descr=\"${altText}\"/><wp:cNvGraphicFramePr><a:graphicFrameLocks xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" noChangeAspect=\"1\"/></wp:cNvGraphicFramePr>"
						+ "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
						+ " <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
						+ " <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><pic:nvPicPr><pic:cNvPr id=\"${id2}\" name=\"${filenameHint}\"/><pic:cNvPicPr/></pic:nvPicPr>"
						+ "<pic:blipFill><a:blip " + type + "=\"${rEmbedId}\">"
//		           + " <a:extLst><a:ext uri=\"{28A0092B-C50C-407E-A947-70E740481C1C}\"><a14:useLocalDpi xmlns:a14=\"http://schemas.microsoft.com/office/drawing/2010/main\" val=\"0\"/></a:ext></a:extLst>"
						+ "</a:blip><a:stretch><a:fillRect/></a:stretch></pic:blipFill>"
						+ "<pic:spPr><a:xfrm><a:off x=\"0\" y=\"0\"/><a:ext cx=\"${cx}\" cy=\"${cy}\"/></a:xfrm><a:prstGeom prst=\"rect\"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData>"
						+ "</a:graphic>"
//		      + "<wp14:sizeRelH relativeFrom=\"page\"><wp14:pctWidth>0</wp14:pctWidth></wp14:sizeRelH><wp14:sizeRelV relativeFrom=\"page\"><wp14:pctHeight>0</wp14:pctHeight></wp14:sizeRelV>"
						+ "</wp:anchor>";



		java.util.HashMap<String, String> mappings = new java.util.HashMap<String, String>();

		mappings.put("cx", Long.toString(cx));
		mappings.put("cy", Long.toString(cy));
		mappings.put("filenameHint", filenameHint);
		mappings.put("altText", altText);
		mappings.put("rEmbedId", this.getRelLast().getId());
		mappings.put("id1", Integer.toString(id1));
		mappings.put("id2", Integer.toString(id2));
		mappings.put("posHOffset", Long.toString(posHOffset));
		mappings.put("posVOffset", Long.toString(posVOffset));
		Object o = org.docx4j.XmlUtils.unmarshallFromTemplate(ml, mappings);
		Anchor anchor = (Anchor) ((JAXBElement) o).getValue();

		return anchor;
	}
}
