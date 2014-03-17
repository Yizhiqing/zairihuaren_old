package com.zairihuaren;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RateInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// JSONObject json = new JSONObject();
		// JSONArray addresses = new JSONArray();
		// JSONObject address;
		// try {
		// int count = 15;
		//
		// for (int i = 0; i < count; i++) {
		// address = new JSONObject();
		// address.put("CustomerName", "Decepticons" + i);
		// address.put("AccountId", "1999" + i);
		// address.put("SiteId", "1888" + i);
		// address.put("Number", "7" + i);
		// address.put("Building", "StarScream Skyscraper" + i);
		// address.put("Street", "Devestator Avenue" + i);
		// address.put("City", "Megatron City" + i);
		// address.put("ZipCode", "ZZ00 XX1" + i);
		// address.put("Country", "CyberTron" + i);
		// addresses.put(address);
		// }
		// json.put("Addresses", addresses);
		// } catch (JSONException jse) {
		//
		// }

		resp.setContentType("application/json");
		String data = getJSON("http://rate-exchange.appspot.com/currency?from=JPY&to=CNY&q=100");
		resp.getWriter().write(data);
	}

	public String getJSON(String url) {
		try {
			URL u = new URL(url);
			HttpURLConnection c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-length", "0");
			c.setUseCaches(false);
			c.setAllowUserInteraction(false);
			c.setConnectTimeout(3000);
			c.setReadTimeout(3000);
			c.connect();
			int status = c.getResponseCode();

			switch (status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(
						c.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				return sb.toString();
			}

		} catch (MalformedURLException ex) {
			// Logger.getLogger(DebugServer.class.getName()).log(Level.SEVERE,
			// null, ex);
		} catch (IOException ex) {
			// Logger.getLogger(DebugServer.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
		return null;
	}
}
