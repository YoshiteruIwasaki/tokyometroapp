package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class TableView extends Controller {

    public static Result trainInformation() {
        return ok(table.render());
    }

}