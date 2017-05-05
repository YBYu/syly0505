package com.zzc.web.travel.util;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordTools {
	public void word(String file, String newFile) {
		try {
			OPCPackage pack = POIXMLDocument.openPackage(file);
			XWPFDocument doc = new XWPFDocument(pack);
			List<XWPFParagraph> paragraphs = doc.getParagraphs();
			System.out.println(paragraphs.size());
			for (XWPFParagraph tmp : paragraphs) {
				System.out.println(tmp.getParagraphText());
				List<XWPFRun> runs = tmp.getRuns();
				for (XWPFRun aa : runs) {
					System.out.println("XWPFRun-Text:" + aa.getText(0));
					if ("$name".equals(aa.getText(0))) {
						aa.setText("必先利", 0);
					}
				}
			}

			FileOutputStream fos = new FileOutputStream(newFile);
			doc.write(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WordTools tools = new WordTools();
		try {
			tools.word("d:\\docx\\test.docx", "d:\\docx\\yya.docx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}