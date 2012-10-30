package com.monetization
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.methods.GetMethod;

import org.apache.commons.httpclient.methods.StringRequestEntity;

import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.Header;

import grails.converters.JSON

import net.sf.json.JSONObject

import net.sf.json.groovy.JsonSlurper
class AdminController {
    static accountId = "08e2512ed30c4ab18d316f233f3976fb"

    static apiKey = "e63f4ea8306b4fff941e75080d764091"

    static sessionId = ""

    static generateSession() {

        String createSubAcctUrl = "https://api.inmobi.com/v1.0/generatesession/generate";

        GetMethod method = new GetMethod(createSubAcctUrl);

        try {

            method.setRequestHeader("Content-Type", "application/json");

            method.addRequestHeader("secretKey", apiKey);

            method.addRequestHeader("username", "vendor@shephertz.co.in");

            method.addRequestHeader("password", "shep@123!");

            HttpClient client = new HttpClient();

            int abc = client.executeMethod(method);

            println " generate session REsponse ${abc}"

            def result = new String(method.getResponseBody());

            def jsonBody = new JsonSlurper().parseText(result)

            sessionId = jsonBody.respList[0].sessionId

            println "Received session Id ${sessionId}"

            //return sessionID

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static void getPubReport(timeFrame) {

        PostMethod method = new PostMethod("https://api.inmobi.com/v1.0/reporting/publisher.json");

        // String getReportJson = "{\"reportRequest\": {\"metrics\":[\"clicks\",\"earnings\",\"adImpressions\"],\"timeFrame\":\"2012-01-01:2012-10-30\",\"filterBy\": [{ \"filterName\": \"siteId\", \"filterValue\": [\"4028cbff3aab0518013aab9e42b00017\"],\"comparator\": \"in\",\"groupBy\":[\"country\",\"site\",\"date\"],\"orderBy\":[\"date\"]}}";
        //String getReportJson = "{\"reportRequest\": {\"metrics\": [], \"groupBy\": [\"site\", \"country\" ], \"timeFrame\": \"2012-01-01:2012-10-30\", \"filterBy\": [{\"filterName\": \"siteName\", \"filterValue\": [\"appClay\"], \"comparator\": \"in\" } ] }}"
        String getReportJson = "{\"reportRequest\": {\"metrics\":[],\"timeFrame\":\"${timeFrame}\",\"groupBy\":[\"site\",\"date\"],\"orderBy\":[\"date\"]}}";

        try {
            StringRequestEntity reqEntity = new StringRequestEntity(getReportJson, "application/json", "UTF-8");
            method.setRequestEntity(reqEntity);
            method.setRequestHeader("Content-Type", "application/json");

            method.addRequestHeader("accountId", accountId);

            method.addRequestHeader("secretKey", apiKey);

            if(sessionId.equals("")){

                generateSession();

            }

            method.addRequestHeader("sessionId", sessionId);
            HttpClient client = new HttpClient();
            int respCode = client.executeMethod(method);
            String response = method.getResponseBodyAsString();
            println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getSiteDetail(siteId) {
        String getSiteDetailUrl = "https://api.inmobi.com/publisherservices/v1.0/{pubId}/site.json";
        String pubAccountId = "b12a3c95ba884145994fed27ea2d9a58";
        String url = getSiteDetailUrl.replace("{pubId}", pubAccountId);
        PostMethod method = new PostMethod(url);
        String requestJson = "{\"siteIds\": {\"siteIds\":[\"4028cbff3aab0518013aab9e42b00017\"]}}";
        try {
            StringRequestEntity reqEntity = new StringRequestEntity(
                requestJson, "application/json", "UTF-8");
            method.setRequestEntity(reqEntity);
            method.setRequestHeader("Content-Type", "application/json");

            method.addRequestHeader("accountId", accountId);

            method.addRequestHeader("secretKey", apiKey);

            if(sessionId.equals("")){

                generateSession();

            }

            method.addRequestHeader("sessionId", sessionId);
            HttpClient client = new HttpClient();
            int abc = client.executeMethod(method);

            println " getsitedetail REsponse ${abc}"

            println new String(method.getResponseBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    def  getSiteSummary() {
        String getSiteUrl = "https://api.inmobi.com/publisherservices/v1.0/pubsites.json";
        PostMethod method = new PostMethod(getSiteUrl);
        String requestJson = "{\"mediaProperty\": {\"accountIds\": []}}";
        def body;
        try {
            StringRequestEntity reqEntity = new StringRequestEntity(
                requestJson, "application/json", "UTF-8");
            method.setRequestEntity(reqEntity);
            method.setRequestHeader("Content-Type", "application/json");
            //setHeaderParams(method);
            method.addRequestHeader("accountId", accountId);

            method.addRequestHeader("secretKey", apiKey);

            if(sessionId.equals("")){

                generateSession();

            }

            method.addRequestHeader("sessionId", sessionId);
            HttpClient client = new HttpClient();
            def abc = client.executeMethod(method);
            println " getsitesummary REsponse ${abc}"

            body =  new String(method.getResponseBody());
            return body

        } catch (Exception e) {
            sessionId = "";
            println "INside Catch"
            getSiteSummary()
        }
        
    }

    def createAcc() {
        println "called create acc..."
        String createSubAcctUrl = "https://api.inmobi.com/publisherservices/v1.0/account.json";

        PostMethod method = new PostMethod(createSubAcctUrl);

        String subAcctCreateJson = "{\"subAccountDTO\":{\"name\":\"Naveen\",\"terms\":\"Yes\",\"subAccountProfile\":{\"website\":\"http://www.shephertz.com\",\"contact\":{\"phone\":\"9876543210\",\"otherPhone\":\"9342511111\",\"address\":\"Junglee Maharaja Road\",\"city\":\"Pune\",\"country\":43,\"otherEmail\":\"testacct@api.com\"},\"langPref\":1,\"user\":{\"email\":\"jatin.chauhan@shephertz.co.in\",\"firstName\":\"jatin\",\"lastName\":\"chauhan\",\"contactDetail\":{\"phone\":\"9999988888\",\"otherPhone\":\"9632212345\",\"country\":101,\"address\":\"MG Road\",\"city\":\"Bangalore\",\"messenger\":{\"id\":\"mm@yahoo.com\",\"type\":1}}}}}}}";

        //String subAcctCreateJson = "{\"subAccountDTO\": {\"name\": \"alok\",\"terms\": \"Yes\",\"subAccountProfile\": {\"contact\": {\"country\": 41},\"user\": { \"email\": \"sushubdh@gmail.co.in\",\"contactDetail\": {\"phone\": \"9999988888\",\"country\": 41}}}}}";

        try {

            StringRequestEntity reqEntity = new StringRequestEntity(subAcctCreateJson, "application/json", "UTF-8");

            method.setRequestEntity(reqEntity);

            method.setRequestHeader("Content-Type", "application/json");

            method.addRequestHeader("accountId", accountId);

            method.addRequestHeader("secretKey", apiKey);

            if(sessionId.equals("")){

                generateSession();

            }

            method.addRequestHeader("sessionId", sessionId);

            HttpClient client = new HttpClient();

            int abc = client.executeMethod(method);

            println " createacc REsponse ${abc}"

            println new String(method.getResponseBody());

        } catch (Exception e) {

            sessionId = "";

            createAcc();

            //e.printStackTrace();

        }

    }

    def index = {
        
    }

    def summary = {
        def id =  getSiteSummary()
        def json  = JSON.parse(id)
        def resList = json.response.respList
        return [resList:resList]
    }

    def viewDetails = {
        def timeFrame = "2012-01-01:2012-10-30"
        getPubReport(timeFrame)
    }
}
