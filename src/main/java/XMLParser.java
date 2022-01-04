import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XMLParser {

	static char[] tblTokenStart = new char[] { '<', 't', 'a', 'b', 'l', 'e', '>' };
	static char[] tblTokenEnd = new char[] { '<', '/', 't', 'a', 'b', 'l', 'e', '>' };
	static String header = null;
	
	static void readXML() {
		
		// well formed xml
		File f = new File("C:\\Users\\vamsi\\Documents\\input.txt"); 
		try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr);) {
			int c = 0;
			int startTokenCounter = 0;
			int n = 0;
			int tableStart = -1, tableEnd = -1;

			StringBuilder sb = new StringBuilder("");
			
			while ((c = br.read()) != -1) // Read char by char
			{
				// look for <table> start token
				
				// n represents the position of current character in the file
				n++;
				char ch = (char) c;
				//handle spaces in tags like <table >; since it's well formed we will not have tags like 't able'
				if (ch == ' ') continue;
				
				if (ch == tblTokenStart[startTokenCounter]) {
					if (tableStart == -1) // assign just once
						tableStart = n;
					
					// accumulate table contents
					sb.append(ch);
					startTokenCounter++;
				} else {
					// no match, reset
					startTokenCounter = 0;
					tableStart = -1;
					sb = new StringBuilder("");
				}

				if (startTokenCounter == tblTokenStart.length) { // Found <table> token start at tableStart

					int endTokenCounter = 0;
					
					// look for </table> end token
					while ((c = br.read()) != -1) { 

						ch = (char) c;
						// handle spaces in tags like </ table >; since it's well formed we will not have tags like 't able'
						if (ch == ' ') continue;
						
						// accumulate table contents
						sb.append(ch);

						if (ch == tblTokenEnd[endTokenCounter]) {
							if (tableEnd == -1)
								tableEnd = n;
							endTokenCounter++;
						} else {
							// no match, reset
							endTokenCounter = 0;
							tableEnd = -1;
						}

						if (endTokenCounter == tblTokenEnd.length) {
							tableEnd = tableEnd + endTokenCounter;
							
							// printing header just once
							if (header == null) {
								header = parseHeader(sb.toString());
								System.out.println(header);
							}

							// transform table data into a csv rows -- [can submit table to a pipeline in prod]
							transform(sb.toString());
							
							// go for next <table> start and then end
							startTokenCounter = 0;
							endTokenCounter = 0;
							break;
						}
						n++;
					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * parse header -- using Jsoup library
	 * 
	 * @param table
	 * @return
	 */
	static String parseHeader(String table) {
		Document doc = Jsoup.parseBodyFragment(table);

		Elements rows = doc.getElementsByTag("tr");
		StringBuilder line = new StringBuilder();
		;
		for (Element row : rows) {
			Elements cells = row.getElementsByTag("th");
			for (Element cell : cells) {
				line.append(cell.text().concat(", "));
			}

		}
		return line.toString();
	}

	/**
	 * Transform each table td elements -- using Jsoup library
	 * 
	 * @param html table
	 */
	static void transform(String table) {

		Document doc = Jsoup.parseBodyFragment(table);
		Elements rows = doc.getElementsByTag("tr");
		for (Element row : rows) {
			StringBuilder line = new StringBuilder();
			Elements cells = row.getElementsByTag("td");
			for (Element cell : cells) {
				line.append(cell.text().concat(", "));
			}
			// avoiding empty line for <th> row
			if (cells.hasText())
				System.out.println(line.toString());
		}
	}

	public static void main(String[] args) {

		readXML();
	}
}