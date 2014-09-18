package controllers;

import play.Play;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

public class Api extends Controller {

	public static Promise<Result> trainInformation() {
		String apikey = Play.application().configuration()
				.getString("application.apikey");
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.tokyometroapp.jp/api/v2/datapoints?");
		builder.append("rdf:type=odpt:TrainInformation");
		builder.append("&");
		builder.append("acl:consumerKey=");
		builder.append(apikey);

		final Promise<Result> resultPromise = WS.url(builder.toString()).get()
				.map(new Function<WSResponse, Result>() {
					public Result apply(WSResponse response) {
						return ok(response.asJson());
					}
				});
		return resultPromise;
	}

}