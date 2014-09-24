package controllers;

import play.Logger;
import play.Play;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

public class Api extends Controller {

	public static Promise<Result> index(String type) {
		String apikey = Play.application().configuration()
				.getString("application.apikey");
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.tokyometroapp.jp/api/v2/datapoints?");
		builder.append("rdf:type=");
		builder.append(type);
		builder.append("&");
		builder.append("acl:consumerKey=");
		builder.append(apikey);

		Logger.debug(builder.toString());

		final Promise<Result> resultPromise = WS.url(builder.toString()).get()
				.map(new Function<WSResponse, Result>() {
					public Result apply(WSResponse response) {
						if (response.getStatus() == 200) {
							return ok(response.asJson());
						}
						return ok("");
					}
				});
		return resultPromise;
	}

	public static Promise<Result> trainInformation() {
		String apikey = Play.application().configuration()
				.getString("application.apikey");
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.tokyometroapp.jp/api/v2/datapoints?");
		builder.append("rdf:type=odpt:TrainInformation");
		builder.append("&");
		builder.append("acl:consumerKey=");
		builder.append(apikey);

		Logger.debug(builder.toString());

		final Promise<Result> resultPromise = WS.url(builder.toString()).get()
				.map(new Function<WSResponse, Result>() {
					public Result apply(WSResponse response) {
						if (response.getStatus() == 200) {
							return ok(response.asJson());
						}
						return ok("");
					}
				});
		return resultPromise;
	}

}
