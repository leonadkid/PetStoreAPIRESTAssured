package api.endpoints;

public class Routes
{
    private Routes() {}

    public static String baseurl = "https://petstore.swagger.io/v2";
    public static  String postUrl = baseurl + "/user";
    public static  String getUrl = baseurl + "/user/{username}";
    public static  String updateUrl = baseurl + "/user/{username}";
    public static  String deleteUrl = baseurl + "/user/{username}";
}
