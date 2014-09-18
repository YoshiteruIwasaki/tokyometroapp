package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class View extends Controller {

    public static Result trainInformation() {
        return ok(trainInformation.render());
    }

}