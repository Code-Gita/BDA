package DailyEntries;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class PdfController {

	/*
	 * Contains all the fonts default is andalus_r
	 */
	public BaseFont font(String myFont) {
		BaseFont andalus_r = null;
		BaseFont arialRounded;
		BaseFont arialUnicode;
		BaseFont calibriLight;
		BaseFont arial;
		try {
			// create a font that will be embedded
			HashMap<String, BaseFont> hm = new HashMap<>();
			andalus_r = BaseFont.createFont("c:/windows/fonts/andlso.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			arialRounded = BaseFont.createFont("c:/windows/fonts/ARLRDBD.TTF", BaseFont.WINANSI, BaseFont.EMBEDDED);
			arialUnicode = BaseFont.createFont("c:/windows/fonts/ARIALUNI.TTF", BaseFont.WINANSI, BaseFont.EMBEDDED);
			calibriLight = BaseFont.createFont("c:/windows/fonts/CalibriL.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			arial = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
			hm.put(new String("andalus"), andalus_r);
			hm.put(new String("arialRounded"), arialRounded);
			hm.put(new String("arialUnicode"), arialUnicode);
			hm.put(new String("calibriLight"), calibriLight);
			hm.put(new String("arial"), arial);
			Set<Entry<String, BaseFont>> set = hm.entrySet();
			Iterator<Entry<String, BaseFont>> i = set.iterator();
			while (i.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry me = (Map.Entry) i.next();
				if (me.getKey().equals(myFont)) {
					return (BaseFont) me.getValue();
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return andalus_r;
	}

}
