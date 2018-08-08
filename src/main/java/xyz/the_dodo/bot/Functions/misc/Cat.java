package xyz.the_dodo.bot.Functions.misc;

import net.dv8tion.jda.core.entities.Message;
import xyz.the_dodo.bot.Functions.IFunction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cat extends IFunction
{
	public Cat(String command, String description, String usage) {
		super(command, description, usage);
		this.isService = true;
	}

	@Override
	public void trigger(Message message) {
		try {
			//TODO: Make better
			String url = "http://thecatapi.com/api/images/get?type=png";
			URL obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (InputStream is = obj.openStream()) {
				byte[] byteChunk = new byte[16384];
				int n;

				while ((n = is.read(byteChunk)) > 0) {
					baos.write(byteChunk, 0, n);
				}
			} catch (IOException e) {
				System.err.printf("Failed while reading bytes from %s: %s", obj.toExternalForm(), e.getMessage());
				e.printStackTrace();
			}

			message.getTextChannel().sendFile(baos.toByteArray(), "cat.png").queue();
		} catch (Exception e) {
			//TODO: bugReporting
		}
	}
}
