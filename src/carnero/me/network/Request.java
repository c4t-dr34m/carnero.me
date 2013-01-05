package carnero.me.network;

import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Request {

	public String getJSON(String address) {
		String response;

		try {
			final HttpGet get = new HttpGet(address);
			final URL url = new URL(get.getRequestLine().getUri());
			final URLConnection connection = url.openConnection();
			final InputStream stream = connection.getInputStream();

			final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			final StringBuilder builder = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}

			response = builder.toString();
		} catch (Exception e) {
			return null;
		}

		return response;
	}
}
