/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.query;

import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import java.text.DateFormat;
import java.util.Calendar;
import com.jaunt.Elements;
import com.jaunt.Element;
import java.util.Dictionary;
import java.util.Hashtable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Abhay
 */
public class QueryHandeler {

    public String Links[];

    public QueryHandeler(String links[]) {
        Links = links;
    }

    public String[] variablesJudis(String page) {
        String var[] = new String[3];
        int i = page.indexOf("__VIEWSTATE\" value=");
        int j = page.indexOf("\"", i + 125);
        String temp1 = page.substring(i + 20, j);
        var[0] = temp1.replaceAll("\\=", "%3D");

        i = page.indexOf("__VIEWSTATEGENERATOR\" value");
        j = page.indexOf("\"", i + 35);
        var[1] = page.substring(i + 29, j);
        System.out.println(page.substring(i + 29, j));

        i = page.indexOf("__EVENTVALIDATION\" value=");
        j = page.indexOf("\"", i + 40);
        String temp3 = page.substring(i + 26, j);
        var[2] = temp3.replaceAll("\\/", "%2F");
        System.out.println(page.substring(i + 26, j));
        return var;
    }

    public void queryBuilder()throws Exception {
        try {
            // the dates are in dd mm yyyy format
            String dayPresent = "12";
            String monthPresent = "07";
            String yearPresent = "2015";
            String dayFrom = "01";
            String monthFrom = "01";
            String yearFrom = "2014";
            /*if(Integer.parseInt(yearFrom) !=  Integer.parseInt(yearPresent))
            {    
            for( int iter = Integer.parseInt(yearFrom); iter <=  Integer.parseInt(yearPresent); iter++){
                dayPresent = "31";
                monthPresent = "12";
                yearPresent = String.valueOf(iter);;*/
                
                
            
            for (int iterator = 0; iterator < Links.length; iterator++) {
                UserAgent userAgent = new UserAgent();
                
                
                //Andhra Pradesh
                // Chattishgarh
                // Jammu
                // Kerala
                // Chennai
                // Sri Nagar
                if (Links[iterator] == "http://judis.nic.in/judis_andhra/judqry.aspx"
                        || Links[iterator] == "http://judis.nic.in/judis_chattisgarh/judqry.aspx"
                        || Links[iterator] == "http://judis.nic.in/Judis_Jammu/judqry.aspx"
                        || Links[iterator] == "http://judis.nic.in/judis_kerala/judqry.aspx"
                        || Links[iterator] == "http://judis.nic.in/judis_chennai/judqry.aspx"
                        || Links[iterator] == "http://judis.nic.in/Judis_SriNagar/judqry.aspx") {
                    //UserAgent userAgent = new UserAgent();
                    userAgent.visit("http://judis.nic.in/judis_andhra/judqry.aspx");
                    String page = userAgent.doc.innerXML();
                    //System.out.println("DOCUMENT:\n" + page);
                    String var[] = variablesJudis(page);
                    String requestMessage = "__EVENTARGUMENT=&__VIEWSTATE=" + var[0].replaceAll("\\/", "%2F") + "&__VIEWSTATEGENERATOR=" + var[1] + "&__EVENTVALIDATION=" + var[2].replaceAll("\\+", "%2B") + "&txttitle=Justice&selfday=" + dayFrom + "&selfmonth=" + monthFrom + "&selfyear=" + yearFrom + "&seltday=" + dayPresent + "&seltmonth=" + monthPresent + "&seltyear=" + yearPresent + "&button=Submit";
                    userAgent.sendPOST("http://judis.nic.in/judis_andhra/judqry.aspx", requestMessage);
          
                    String data = userAgent.doc.innerXML();
                    //System.out.println("DOCUMENT:\n" + data);
                    userAgent.visit("http://judis.nic.in/judis_andhra/judseq.aspx");
                    System.out.println(userAgent.doc.innerXML());
                    System.out.println(userAgent.doc.innerXML());
                    if(data.contains("No Records Found"))
                        ;//error
                    else
                    {
                        //System.out.println("DOCUMENT:\n" + data);
                        System.out.println("check");
                        var = variablesJudis(data);
                        System.out.println("check");
                        while (true){
                            int index = 0;
                            int foundAt = -1;
                            int count = 1;
                            System.out.println("FGfsdfdgfvsdvdsfv");
                            while (data.indexOf("a href=\"javascript:__doPostBack('DataGrid1$ctl19$ctl", index) != -1) {
                                System.out.println("FG");
                                foundAt = data.indexOf("<a href=\"javascript:__doPostBack(\'DataGrid1$ctl19$ct1", index);
                                if(count<10)
                                    requestMessage = "__EVENTTARGET=DataGrid1%24ctl19%24ctl" + "0"+ String.valueOf(count) + "&__EVENTARGUMENT=&__VIEWSTATE=" + var[0].replaceAll("\\/", "%2F") + "&__VIEWSTATEGENERATOR=" + var[1] + "&__EVENTVALIDATION=" + var[2].replaceAll("\\+", "%2B");
                                    //requestMessage = "__EVENTTARGET=DataGrid1%24ctl19%24ctl" + "0"+ String.valueOf(count)+ "&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTQyODk3NTY3MQ9kFgICAw9kFggCAQ88KwALAQAPFggeCERhdGFLZXlzFgAeC18hSXRlbUNvdW50Ag8eCVBhZ2VDb3VudAJdHhVfIURhdGFTb3VyY2VJdGVtQ291bnQC8gpkFgJmD2QWHgICD2QWEmYPDxYCHgRUZXh0BQUxMjY5MWRkAgEPDxYCHwQFATFkZAICDw8WAh8EBSJDRU5UUkFMIEVYQ0lTRSBBUFBFQUwgTm8uMjQgb2YyMDA0ZGQCAw9kFgJmDw8WAh8EBVJUaGVDb21taXNzaW9uZXIsIEN1c3RvbXMgJiBDZW50cmFsRXhjaSBWcy4gTS9zLlN1cmFuYSBUZWxlY29tIExpbWl0ZWQsIElJIEZsb29yLFN1ZGQCBA9kFgJmDw8WBB8EBVJUaGVDb21taXNzaW9uZXIsIEN1c3RvbXMgJiBDZW50cmFsRXhjaSBWcy4gTS9zLlN1cmFuYSBUZWxlY29tIExpbWl0ZWQsIElJIEZsb29yLFN1HgtOYXZpZ2F0ZVVybAUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNjkxZGQCBQ9kFgJmDw8WAh8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgRElMSVBCLkJIT1NBTEUgQU5EIFRIRSBIT04%2FQkxFIFNSSUpVLCBPZGQCBg9kFgJmDw8WBB8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgRElMSVBCLkJIT1NBTEUgQU5EIFRIRSBIT04%2FQkxFIFNSSUpVLCBPHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjY5MWRkAgcPDxYCHwQFCjQvLy8vLzIwMTVkZAIIDw8WAh8EBQdqdXN0aWNlZGQCAw9kFhJmDw8WAh8EBQUxMjU2NGRkAgEPDxYCHwQFATJkZAICDw8WAh8EBSdDSVZJTCBSRVZJU0lPTiBQRVRJVElPTiBOby4zNjE4IE9GIDIwMTRkZAIDD2QWAmYPDxYCHwQFIEt1bmt1bnRsYSBOYXIgVnMuIFN5ZWQgWmFpbnVsYWJ1ZGQCBA9kFgJmDw8WBB8EBSBLdW5rdW50bGEgTmFyIFZzLiBTeWVkIFphaW51bGFidR8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI1NjRkZAIFD2QWAmYPDxYCHwQFM0NvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSBBLlJBTUFMSU5HRVNXQVJBIFJBT2RkAgYPZBYCZg8PFgQfBAUzQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIEEuUkFNQUxJTkdFU1dBUkEgUkFPHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjU2NGRkAgcPDxYCHwQFCjMvLy8vLzIwMTVkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBA9kFhJmDw8WAh8EBQUxMDkxOWRkAgEPDxYCHwQFATNkZAICDw8WAh8EBSFDcmwuUi5DLk5vLjEyNjMgb2YgMjAwOCBBTkQgQkFUQ0hkZAIDD2QWAmYPDxYCHwQFQFRoZSBTdGF0ZSBvZiBBLlAuIHJlcHJlc2VudGVkIGJ5IHRoZSBQdWIgVnMuIFNoYWlrIEdob3VzZSBNb2hpdWRkZAIED2QWAmYPDxYEHwQFQFRoZSBTdGF0ZSBvZiBBLlAuIHJlcHJlc2VudGVkIGJ5IHRoZSBQdWIgVnMuIFNoYWlrIEdob3VzZSBNb2hpdWQfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEwOTE5ZGQCBQ9kFgJmDw8WAh8EBUNDb3JhbTogVEhFIEhPTidCTEUgU1JJIEpVU1RJQ0UgTC5OQVJBU0lNSEEgUkVERFkgQU5EIFRIRSBIT04nQkxFIFNSZGQCBg9kFgJmDw8WBB8EBUNDb3JhbTogVEhFIEhPTidCTEUgU1JJIEpVU1RJQ0UgTC5OQVJBU0lNSEEgUkVERFkgQU5EIFRIRSBIT04nQkxFIFNSHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMDkxOWRkAgcPDxYCHwQFCjAzL28yLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBQ9kFhJmDw8WAh8EBQUxMjQyOWRkAgEPDxYCHwQFATRkZAICDw8WAh8EBRxXUklUIFBFVElUSU9OIE5PLjU3ODQgT0YyMDE0ZGQCAw9kFgJmDw8WAh8EBUREci5QLkhhcnNoYVZhcmRoYW4gYW5kIG90IFZzLiBHb3Zlcm5tZW50IG9mIEluZGlhLCBNaW5pc3RyeSBvZkhlYWx0aGRkAgQPZBYCZg8PFgQfBAVERHIuUC5IYXJzaGFWYXJkaGFuIGFuZCBvdCBWcy4gR292ZXJubWVudCBvZiBJbmRpYSwgTWluaXN0cnkgb2ZIZWFsdGgfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDI5ZGQCBQ9kFgJmDw8WAh8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZZGQCBg9kFgJmDw8WBB8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQyOWRkAgcPDxYCHwQFCjMxLzEyLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBg9kFhJmDw8WAh8EBQUxMjQzMGRkAgEPDxYCHwQFATVkZAICDw8WAh8EBRxXUklUIFBFVElUSU9OIE5PLjU3ODQgT0YyMDE0ZGQCAw9kFgJmDw8WAh8EBUREci5QLkhhcnNoYVZhcmRoYW4gYW5kIG90IFZzLiBHb3Zlcm5tZW50IG9mIEluZGlhLCBNaW5pc3RyeSBvZkhlYWx0aGRkAgQPZBYCZg8PFgQfBAVERHIuUC5IYXJzaGFWYXJkaGFuIGFuZCBvdCBWcy4gR292ZXJubWVudCBvZiBJbmRpYSwgTWluaXN0cnkgb2ZIZWFsdGgfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDMwZGQCBQ9kFgJmDw8WAh8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZZGQCBg9kFgJmDw8WBB8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQzMGRkAgcPDxYCHwQFCjMxLzEyLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBw9kFhJmDw8WAh8EBQUxMjQzMWRkAgEPDxYCHwQFATZkZAICDw8WAh8EBR5XUklUIFBFVElUSU9OIE5PLjEzODMyIE9GIDIwMTNkZAIDD2QWAmYPDxYCHwQFPEtNSyBFdmVudCBNYW5hZ2VtZW50IFZzLiBUaGUgQ29tbWlzc2lvbmVyIG9mIENvbW1lcmNpYWwgVGF4ZWRkAgQPZBYCZg8PFgQfBAU8S01LIEV2ZW50IE1hbmFnZW1lbnQgVnMuIFRoZSBDb21taXNzaW9uZXIgb2YgQ29tbWVyY2lhbCBUYXhlHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQzMWRkAgUPZBYCZg8PFgIfBAVGQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEUgUywgWWRkAgYPZBYCZg8PFgQfBAVGQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEUgUywgWR8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MzFkZAIHDw8WAh8EBQozMS8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAggPZBYSZg8PFgIfBAUFMTI0MzRkZAIBDw8WAh8EBQE3ZGQCAg8PFgIfBAUpV1JJVCBQRVRJVElPTiBOT3MuMzExMzggIE9GIDIwMTJBTkQgQkFUQ0hkZAIDD2QWAmYPDxYCHwQFSlRoZVBydWRlbnRpYWwgQ29vcGVyYXRpdmUgQmFuayBWcy4gVGhlIEEuUC4gQ29vcGVyYXRpdmVUcmlidW5hbCBhdCBNSiBNYXJrZGQCBA9kFgJmDw8WBB8EBUpUaGVQcnVkZW50aWFsIENvb3BlcmF0aXZlIEJhbmsgVnMuIFRoZSBBLlAuIENvb3BlcmF0aXZlVHJpYnVuYWwgYXQgTUogTWFyax8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MzRkZAIFD2QWAmYPDxYCHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRVJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEVTUkksIFlkZAIGD2QWAmYPDxYEHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRVJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEVTUkksIFkfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDM0ZGQCBw8PFgIfBAUKMzEvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAIJD2QWEmYPDxYCHwQFBTEyNDM3ZGQCAQ8PFgIfBAUBOGRkAgIPDxYCHwQFFUMuUi5QLk5PLjQ5MDQgT0YgMjAxM2RkAgMPZBYCZg8PFgIfBAUjUC5QdXNocGFtYWxhIFIgVnMuIEphbmdhIFJhZ2hhdmEgUmVkZAIED2QWAmYPDxYEHwQFI1AuUHVzaHBhbWFsYSBSIFZzLiBKYW5nYSBSYWdoYXZhIFJlHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQzN2RkAgUPZBYCZg8PFgIfBAUrQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFNBTkpBWSBLVU1BUmRkAgYPZBYCZg8PFgQfBAUrQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFNBTkpBWSBLVU1BUh8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MzdkZAIHDw8WAh8EBQozMS8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAgoPZBYSZg8PFgIfBAUFMTI0NjBkZAIBDw8WAh8EBQE5ZGQCAg8PFgIfBAUoQ0lWSUwgUkVWSVNJT04gUEVUSVRJT04gTm8uMzg3MiBPRiAyMDE0LmRkAgMPZBYCZg8PFgIfBAUxUy5MYSBWcy4gTS9zIFJlbGlhbmNlIEJ1aWxkZXJzLCBIeWRlcmFiYWQgQW5kIG90aGRkAgQPZBYCZg8PFgQfBAUxUy5MYSBWcy4gTS9zIFJlbGlhbmNlIEJ1aWxkZXJzLCBIeWRlcmFiYWQgQW5kIG90aB8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0NjBkZAIFD2QWAmYPDxYCHwQFQ0NvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSBLLkMuQkhBTlUgQU5EIFRIRSBIT04%2FQkxFIFNNVCBKVVNUSUNkZAIGD2QWAmYPDxYEHwQFQ0NvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSBLLkMuQkhBTlUgQU5EIFRIRSBIT04%2FQkxFIFNNVCBKVVNUSUMfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDYwZGQCBw8PFgIfBAUKMzEvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAILD2QWEmYPDxYCHwQFBTEyNTExZGQCAQ8PFgIfBAUCMTBkZAICDw8WAh8EBRpNLkEuQy5NLkEuIE5vLjI1MTIgT0YgMjAwOGRkAgMPZBYCZg8PFgIfBAVGQVBTUlRDLCByZXAuIGJ5IGl0cyBHZW5lcmFsIE1hbmFnZXIsIE11cyBWcy4gQ2hlZWRlIEF0Y2hheWFtbWEgYW5kIG90aGRkAgQPZBYCZg8PFgQfBAVGQVBTUlRDLCByZXAuIGJ5IGl0cyBHZW5lcmFsIE1hbmFnZXIsIE11cyBWcy4gQ2hlZWRlIEF0Y2hheWFtbWEgYW5kIG90aB8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI1MTFkZAIFD2QWAmYPDxYCHwQFMkNvcmFtOiBUSEUgSE9OP0JMRSBEci4gSlVTVElDRSBCLiBTSVZBIFNBTktBUkEgUkFPZGQCBg9kFgJmDw8WBB8EBTJDb3JhbTogVEhFIEhPTj9CTEUgRHIuIEpVU1RJQ0UgQi4gU0lWQSBTQU5LQVJBIFJBTx8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI1MTFkZAIHDw8WAh8EBQozMS8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAgwPZBYSZg8PFgIfBAUFMTI3NTBkZAIBDw8WAh8EBQIxMWRkAgIPDxYCHwQFH1cuUC5Ob3MuMzAzMjcgb2YgMjAxNCBhbmQgYmF0Y2hkZAIDD2QWAmYPDxYCHwQFVVJhaW4gQ0lJIENhcmJvbiAoVml6YWcpIExpbWl0ZWQsIFJlcHJlc2UgVnMuIFRoZSBHb3Zlcm5tZW50IG9mIEFuZGhyYSBQcmFkZXNoLCBSZXByZXNkZAIED2QWAmYPDxYEHwQFVVJhaW4gQ0lJIENhcmJvbiAoVml6YWcpIExpbWl0ZWQsIFJlcHJlc2UgVnMuIFRoZSBHb3Zlcm5tZW50IG9mIEFuZGhyYSBQcmFkZXNoLCBSZXByZXMfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNzUwZGQCBQ9kFgJmDw8WAh8EBS9Db3JhbTogSE9OJ0JMRSBTUkkgSlVTVElDRSBDLlYuIE5BR0FSSlVOQSBSRUREWWRkAgYPZBYCZg8PFgQfBAUvQ29yYW06IEhPTidCTEUgU1JJIEpVU1RJQ0UgQy5WLiBOQUdBUkpVTkEgUkVERFkfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNzUwZGQCBw8PFgIfBAUKMzEvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAIND2QWEmYPDxYCHwQFBTEyNDI2ZGQCAQ8PFgIfBAUCMTJkZAICDw8WAh8EBR9XUklUIFBFVElUSU9OIE5vcy4yOTE4OSBvZiAyMDE0ZGQCAw9kFgJmDw8WAh8EBTRTLlRpbW1hIFZzLiBUaGUgU3RhdGUgb2YgQW5kaHJhIFByYWRlc2gsICBSZXZlbnVlIChFZGQCBA9kFgJmDw8WBB8EBTRTLlRpbW1hIFZzLiBUaGUgU3RhdGUgb2YgQW5kaHJhIFByYWRlc2gsICBSZXZlbnVlIChFHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQyNmRkAgUPZBYCZg8PFgIfBAVDQ29yYW06IFRIRSBIT04%2FQkxFIFRIRSBDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEEgQU5EIGRkAgYPZBYCZg8PFgQfBAVDQ29yYW06IFRIRSBIT04%2FQkxFIFRIRSBDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEEgQU5EIB8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MjZkZAIHDw8WAh8EBQozMC8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAg4PZBYSZg8PFgIfBAUFMTI0MjhkZAIBDw8WAh8EBQIxM2RkAgIPDxYCHwQFHldSSVQgUEVUSVRJT04gTm8uMjcxNzcgb2YgMjAxNGRkAgMPZBYCZg8PFgIfBAVVR01SIEhvdGVscyBhbmQgUmVzb3J0cyBMdGQgUmVwIGJ5IHRoZSBDaCBWcy4gVGhlIFVuaW9uIG9mIEluZGlhIFJlcCBieSBEaXJlY3RvciBHZW5lcmRkAgQPZBYCZg8PFgQfBAVVR01SIEhvdGVscyBhbmQgUmVzb3J0cyBMdGQgUmVwIGJ5IHRoZSBDaCBWcy4gVGhlIFVuaW9uIG9mIEluZGlhIFJlcCBieSBEaXJlY3RvciBHZW5lch8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MjhkZAIFD2QWAmYPDxYCHwQFLENvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSAgUC5OQVZFRU4gUkFPZGQCBg9kFgJmDw8WBB8EBSxDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgIFAuTkFWRUVOIFJBTx8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MjhkZAIHDw8WAh8EBQozMC8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAg8PZBYSZg8PFgIfBAUFMTI0NTNkZAIBDw8WAh8EBQIxNGRkAgIPDxYCHwQFHVdSSVQgUEVUSVRJT04gTm8uNjQ4NCBPRiAyMDA5ZGQCAw9kFgJmDw8WAh8EBTxDaC4gSmFpIFNoYW5rYXIgVnMuIFRoZSBDb21taXNzaW9uZXIgb2YgRW5kb3dtZW50cywgR292ZXJubWVkZAIED2QWAmYPDxYEHwQFPENoLiBKYWkgU2hhbmthciBWcy4gVGhlIENvbW1pc3Npb25lciBvZiBFbmRvd21lbnRzLCBHb3Zlcm5tZR8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0NTNkZAIFD2QWAmYPDxYCHwQFLUNvcmFtOiBIT04%2FQkxFIFNSSSBKVVNUSUNFIENIQUxMQSBLT0RBTkRBIFJBTWRkAgYPZBYCZg8PFgQfBAUtQ29yYW06IEhPTj9CTEUgU1JJIEpVU1RJQ0UgQ0hBTExBIEtPREFOREEgUkFNHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQ1M2RkAgcPDxYCHwQFCjMwLzEyLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCEA9kFhJmDw8WAh8EBQUxMjQ2NGRkAgEPDxYCHwQFAjE1ZGQCAg8PFgIfBAUeV1JJVCBQRVRJVElPTiBOby4gNDAxNTEgb2YyMDE0ZGQCAw9kFgJmDw8WAh8EBVFWYWRsYWtvbmRhU2F0ZWVzaCwgIEthcmltbmFnYXIgRGlzdHJpIFZzLiBUaGUgU3RhdGUgb2ZUZWxhbmdhbmEsUmVwLiBieSBpdHMgUHJpbmNkZAIED2QWAmYPDxYEHwQFUVZhZGxha29uZGFTYXRlZXNoLCAgS2FyaW1uYWdhciBEaXN0cmkgVnMuIFRoZSBTdGF0ZSBvZlRlbGFuZ2FuYSxSZXAuIGJ5IGl0cyBQcmluYx8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0NjRkZAIFD2QWAmYPDxYCHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBUSEVDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEFBTkQgVEgsIFJkZAIGD2QWAmYPDxYEHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBUSEVDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEFBTkQgVEgsIFIfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDY0ZGQCBw8PFgIfBAUKMzAvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAIGDw8WBB8EBRYxMzk0IFJlY29yZHMgcmV0cmlldmVkHgdWaXNpYmxlZ2RkAgoPDxYCHwQFG1NlYXJjaGVkIGZvciBKdWRnZToganVzdGljZWRkAgwPDxYCHwQFCSVqdXN0aWNlJWRkZMKtwI8JRYTEzGQJfmCD0HA%2Fne1p&__VIEWSTATEGENERATOR=DBA9DB93&__EVENTVALIDATION=%2FwEWGgKx8sSFBAK%2B97KkAQK%2F97KkAQLA97KkAQK597KkAQK697KkAQK797KkAQK897KkAQK197KkAQK297KkAQK998b%2FCQK%2B98b%2FCQK%2F98b%2FCQLA98b%2FCQK598b%2FCQK698b%2FCQK798b%2FCQK898b%2FCQK198b%2FCQK298b%2FCQK994ruDwK%2B94ruDwK%2F94ruDwLA94ruDwK594ruDwK694ruD3NAkBlsDV%2FiPE68scbXvxnH4YnG";
                                //System.out.println(requestMessage);
                                else
                                   requestMessage = "__EVENTTARGET=DataGrid1%24ctl19%24ctl" + String.valueOf(count) + "&__EVENTARGUMENT=&__VIEWSTATE=" + var[0].replaceAll("\\/", "%2F") + "&__VIEWSTATEGENERATOR=" + var[1] + "&__EVENTVALIDATION=" + var[2].replaceAll("\\+", "%2B");

                                    //requestMessage = "__EVENTTARGET=DataGrid1%24ctl19%24ctl" + String.valueOf(count)+ "&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTQyODk3NTY3MQ9kFgICAw9kFggCAQ88KwALAQAPFggeCERhdGFLZXlzFgAeC18hSXRlbUNvdW50Ag8eCVBhZ2VDb3VudAJdHhVfIURhdGFTb3VyY2VJdGVtQ291bnQC8gpkFgJmD2QWHgICD2QWEmYPDxYCHgRUZXh0BQUxMjY5MWRkAgEPDxYCHwQFATFkZAICDw8WAh8EBSJDRU5UUkFMIEVYQ0lTRSBBUFBFQUwgTm8uMjQgb2YyMDA0ZGQCAw9kFgJmDw8WAh8EBVJUaGVDb21taXNzaW9uZXIsIEN1c3RvbXMgJiBDZW50cmFsRXhjaSBWcy4gTS9zLlN1cmFuYSBUZWxlY29tIExpbWl0ZWQsIElJIEZsb29yLFN1ZGQCBA9kFgJmDw8WBB8EBVJUaGVDb21taXNzaW9uZXIsIEN1c3RvbXMgJiBDZW50cmFsRXhjaSBWcy4gTS9zLlN1cmFuYSBUZWxlY29tIExpbWl0ZWQsIElJIEZsb29yLFN1HgtOYXZpZ2F0ZVVybAUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNjkxZGQCBQ9kFgJmDw8WAh8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgRElMSVBCLkJIT1NBTEUgQU5EIFRIRSBIT04%2FQkxFIFNSSUpVLCBPZGQCBg9kFgJmDw8WBB8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgRElMSVBCLkJIT1NBTEUgQU5EIFRIRSBIT04%2FQkxFIFNSSUpVLCBPHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjY5MWRkAgcPDxYCHwQFCjQvLy8vLzIwMTVkZAIIDw8WAh8EBQdqdXN0aWNlZGQCAw9kFhJmDw8WAh8EBQUxMjU2NGRkAgEPDxYCHwQFATJkZAICDw8WAh8EBSdDSVZJTCBSRVZJU0lPTiBQRVRJVElPTiBOby4zNjE4IE9GIDIwMTRkZAIDD2QWAmYPDxYCHwQFIEt1bmt1bnRsYSBOYXIgVnMuIFN5ZWQgWmFpbnVsYWJ1ZGQCBA9kFgJmDw8WBB8EBSBLdW5rdW50bGEgTmFyIFZzLiBTeWVkIFphaW51bGFidR8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI1NjRkZAIFD2QWAmYPDxYCHwQFM0NvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSBBLlJBTUFMSU5HRVNXQVJBIFJBT2RkAgYPZBYCZg8PFgQfBAUzQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIEEuUkFNQUxJTkdFU1dBUkEgUkFPHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjU2NGRkAgcPDxYCHwQFCjMvLy8vLzIwMTVkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBA9kFhJmDw8WAh8EBQUxMDkxOWRkAgEPDxYCHwQFATNkZAICDw8WAh8EBSFDcmwuUi5DLk5vLjEyNjMgb2YgMjAwOCBBTkQgQkFUQ0hkZAIDD2QWAmYPDxYCHwQFQFRoZSBTdGF0ZSBvZiBBLlAuIHJlcHJlc2VudGVkIGJ5IHRoZSBQdWIgVnMuIFNoYWlrIEdob3VzZSBNb2hpdWRkZAIED2QWAmYPDxYEHwQFQFRoZSBTdGF0ZSBvZiBBLlAuIHJlcHJlc2VudGVkIGJ5IHRoZSBQdWIgVnMuIFNoYWlrIEdob3VzZSBNb2hpdWQfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEwOTE5ZGQCBQ9kFgJmDw8WAh8EBUNDb3JhbTogVEhFIEhPTidCTEUgU1JJIEpVU1RJQ0UgTC5OQVJBU0lNSEEgUkVERFkgQU5EIFRIRSBIT04nQkxFIFNSZGQCBg9kFgJmDw8WBB8EBUNDb3JhbTogVEhFIEhPTidCTEUgU1JJIEpVU1RJQ0UgTC5OQVJBU0lNSEEgUkVERFkgQU5EIFRIRSBIT04nQkxFIFNSHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMDkxOWRkAgcPDxYCHwQFCjAzL28yLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBQ9kFhJmDw8WAh8EBQUxMjQyOWRkAgEPDxYCHwQFATRkZAICDw8WAh8EBRxXUklUIFBFVElUSU9OIE5PLjU3ODQgT0YyMDE0ZGQCAw9kFgJmDw8WAh8EBUREci5QLkhhcnNoYVZhcmRoYW4gYW5kIG90IFZzLiBHb3Zlcm5tZW50IG9mIEluZGlhLCBNaW5pc3RyeSBvZkhlYWx0aGRkAgQPZBYCZg8PFgQfBAVERHIuUC5IYXJzaGFWYXJkaGFuIGFuZCBvdCBWcy4gR292ZXJubWVudCBvZiBJbmRpYSwgTWluaXN0cnkgb2ZIZWFsdGgfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDI5ZGQCBQ9kFgJmDw8WAh8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZZGQCBg9kFgJmDw8WBB8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQyOWRkAgcPDxYCHwQFCjMxLzEyLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBg9kFhJmDw8WAh8EBQUxMjQzMGRkAgEPDxYCHwQFATVkZAICDw8WAh8EBRxXUklUIFBFVElUSU9OIE5PLjU3ODQgT0YyMDE0ZGQCAw9kFgJmDw8WAh8EBUREci5QLkhhcnNoYVZhcmRoYW4gYW5kIG90IFZzLiBHb3Zlcm5tZW50IG9mIEluZGlhLCBNaW5pc3RyeSBvZkhlYWx0aGRkAgQPZBYCZg8PFgQfBAVERHIuUC5IYXJzaGFWYXJkaGFuIGFuZCBvdCBWcy4gR292ZXJubWVudCBvZiBJbmRpYSwgTWluaXN0cnkgb2ZIZWFsdGgfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDMwZGQCBQ9kFgJmDw8WAh8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZZGQCBg9kFgJmDw8WBB8EBUZDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgUkFNRVNIUkFOR0FOQVRIQU4gQU5EIFRIRSBIT04%2FQkxFIFNSLCBZHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQzMGRkAgcPDxYCHwQFCjMxLzEyLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCBw9kFhJmDw8WAh8EBQUxMjQzMWRkAgEPDxYCHwQFATZkZAICDw8WAh8EBR5XUklUIFBFVElUSU9OIE5PLjEzODMyIE9GIDIwMTNkZAIDD2QWAmYPDxYCHwQFPEtNSyBFdmVudCBNYW5hZ2VtZW50IFZzLiBUaGUgQ29tbWlzc2lvbmVyIG9mIENvbW1lcmNpYWwgVGF4ZWRkAgQPZBYCZg8PFgQfBAU8S01LIEV2ZW50IE1hbmFnZW1lbnQgVnMuIFRoZSBDb21taXNzaW9uZXIgb2YgQ29tbWVyY2lhbCBUYXhlHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQzMWRkAgUPZBYCZg8PFgIfBAVGQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEUgUywgWWRkAgYPZBYCZg8PFgQfBAVGQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEUgUywgWR8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MzFkZAIHDw8WAh8EBQozMS8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAggPZBYSZg8PFgIfBAUFMTI0MzRkZAIBDw8WAh8EBQE3ZGQCAg8PFgIfBAUpV1JJVCBQRVRJVElPTiBOT3MuMzExMzggIE9GIDIwMTJBTkQgQkFUQ0hkZAIDD2QWAmYPDxYCHwQFSlRoZVBydWRlbnRpYWwgQ29vcGVyYXRpdmUgQmFuayBWcy4gVGhlIEEuUC4gQ29vcGVyYXRpdmVUcmlidW5hbCBhdCBNSiBNYXJrZGQCBA9kFgJmDw8WBB8EBUpUaGVQcnVkZW50aWFsIENvb3BlcmF0aXZlIEJhbmsgVnMuIFRoZSBBLlAuIENvb3BlcmF0aXZlVHJpYnVuYWwgYXQgTUogTWFyax8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MzRkZAIFD2QWAmYPDxYCHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRVJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEVTUkksIFlkZAIGD2QWAmYPDxYEHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRVJBTUVTSCBSQU5HQU5BVEhBTiBBTkQgVEhFIEhPTj9CTEVTUkksIFkfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDM0ZGQCBw8PFgIfBAUKMzEvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAIJD2QWEmYPDxYCHwQFBTEyNDM3ZGQCAQ8PFgIfBAUBOGRkAgIPDxYCHwQFFUMuUi5QLk5PLjQ5MDQgT0YgMjAxM2RkAgMPZBYCZg8PFgIfBAUjUC5QdXNocGFtYWxhIFIgVnMuIEphbmdhIFJhZ2hhdmEgUmVkZAIED2QWAmYPDxYEHwQFI1AuUHVzaHBhbWFsYSBSIFZzLiBKYW5nYSBSYWdoYXZhIFJlHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQzN2RkAgUPZBYCZg8PFgIfBAUrQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFNBTkpBWSBLVU1BUmRkAgYPZBYCZg8PFgQfBAUrQ29yYW06IFRIRSBIT04%2FQkxFIFNSSSBKVVNUSUNFIFNBTkpBWSBLVU1BUh8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MzdkZAIHDw8WAh8EBQozMS8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAgoPZBYSZg8PFgIfBAUFMTI0NjBkZAIBDw8WAh8EBQE5ZGQCAg8PFgIfBAUoQ0lWSUwgUkVWSVNJT04gUEVUSVRJT04gTm8uMzg3MiBPRiAyMDE0LmRkAgMPZBYCZg8PFgIfBAUxUy5MYSBWcy4gTS9zIFJlbGlhbmNlIEJ1aWxkZXJzLCBIeWRlcmFiYWQgQW5kIG90aGRkAgQPZBYCZg8PFgQfBAUxUy5MYSBWcy4gTS9zIFJlbGlhbmNlIEJ1aWxkZXJzLCBIeWRlcmFiYWQgQW5kIG90aB8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0NjBkZAIFD2QWAmYPDxYCHwQFQ0NvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSBLLkMuQkhBTlUgQU5EIFRIRSBIT04%2FQkxFIFNNVCBKVVNUSUNkZAIGD2QWAmYPDxYEHwQFQ0NvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSBLLkMuQkhBTlUgQU5EIFRIRSBIT04%2FQkxFIFNNVCBKVVNUSUMfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDYwZGQCBw8PFgIfBAUKMzEvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAILD2QWEmYPDxYCHwQFBTEyNTExZGQCAQ8PFgIfBAUCMTBkZAICDw8WAh8EBRpNLkEuQy5NLkEuIE5vLjI1MTIgT0YgMjAwOGRkAgMPZBYCZg8PFgIfBAVGQVBTUlRDLCByZXAuIGJ5IGl0cyBHZW5lcmFsIE1hbmFnZXIsIE11cyBWcy4gQ2hlZWRlIEF0Y2hheWFtbWEgYW5kIG90aGRkAgQPZBYCZg8PFgQfBAVGQVBTUlRDLCByZXAuIGJ5IGl0cyBHZW5lcmFsIE1hbmFnZXIsIE11cyBWcy4gQ2hlZWRlIEF0Y2hheWFtbWEgYW5kIG90aB8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI1MTFkZAIFD2QWAmYPDxYCHwQFMkNvcmFtOiBUSEUgSE9OP0JMRSBEci4gSlVTVElDRSBCLiBTSVZBIFNBTktBUkEgUkFPZGQCBg9kFgJmDw8WBB8EBTJDb3JhbTogVEhFIEhPTj9CTEUgRHIuIEpVU1RJQ0UgQi4gU0lWQSBTQU5LQVJBIFJBTx8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI1MTFkZAIHDw8WAh8EBQozMS8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAgwPZBYSZg8PFgIfBAUFMTI3NTBkZAIBDw8WAh8EBQIxMWRkAgIPDxYCHwQFH1cuUC5Ob3MuMzAzMjcgb2YgMjAxNCBhbmQgYmF0Y2hkZAIDD2QWAmYPDxYCHwQFVVJhaW4gQ0lJIENhcmJvbiAoVml6YWcpIExpbWl0ZWQsIFJlcHJlc2UgVnMuIFRoZSBHb3Zlcm5tZW50IG9mIEFuZGhyYSBQcmFkZXNoLCBSZXByZXNkZAIED2QWAmYPDxYEHwQFVVJhaW4gQ0lJIENhcmJvbiAoVml6YWcpIExpbWl0ZWQsIFJlcHJlc2UgVnMuIFRoZSBHb3Zlcm5tZW50IG9mIEFuZGhyYSBQcmFkZXNoLCBSZXByZXMfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNzUwZGQCBQ9kFgJmDw8WAh8EBS9Db3JhbTogSE9OJ0JMRSBTUkkgSlVTVElDRSBDLlYuIE5BR0FSSlVOQSBSRUREWWRkAgYPZBYCZg8PFgQfBAUvQ29yYW06IEhPTidCTEUgU1JJIEpVU1RJQ0UgQy5WLiBOQUdBUkpVTkEgUkVERFkfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNzUwZGQCBw8PFgIfBAUKMzEvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAIND2QWEmYPDxYCHwQFBTEyNDI2ZGQCAQ8PFgIfBAUCMTJkZAICDw8WAh8EBR9XUklUIFBFVElUSU9OIE5vcy4yOTE4OSBvZiAyMDE0ZGQCAw9kFgJmDw8WAh8EBTRTLlRpbW1hIFZzLiBUaGUgU3RhdGUgb2YgQW5kaHJhIFByYWRlc2gsICBSZXZlbnVlIChFZGQCBA9kFgJmDw8WBB8EBTRTLlRpbW1hIFZzLiBUaGUgU3RhdGUgb2YgQW5kaHJhIFByYWRlc2gsICBSZXZlbnVlIChFHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQyNmRkAgUPZBYCZg8PFgIfBAVDQ29yYW06IFRIRSBIT04%2FQkxFIFRIRSBDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEEgQU5EIGRkAgYPZBYCZg8PFgQfBAVDQ29yYW06IFRIRSBIT04%2FQkxFIFRIRSBDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEEgQU5EIB8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MjZkZAIHDw8WAh8EBQozMC8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAg4PZBYSZg8PFgIfBAUFMTI0MjhkZAIBDw8WAh8EBQIxM2RkAgIPDxYCHwQFHldSSVQgUEVUSVRJT04gTm8uMjcxNzcgb2YgMjAxNGRkAgMPZBYCZg8PFgIfBAVVR01SIEhvdGVscyBhbmQgUmVzb3J0cyBMdGQgUmVwIGJ5IHRoZSBDaCBWcy4gVGhlIFVuaW9uIG9mIEluZGlhIFJlcCBieSBEaXJlY3RvciBHZW5lcmRkAgQPZBYCZg8PFgQfBAVVR01SIEhvdGVscyBhbmQgUmVzb3J0cyBMdGQgUmVwIGJ5IHRoZSBDaCBWcy4gVGhlIFVuaW9uIG9mIEluZGlhIFJlcCBieSBEaXJlY3RvciBHZW5lch8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MjhkZAIFD2QWAmYPDxYCHwQFLENvcmFtOiBUSEUgSE9OP0JMRSBTUkkgSlVTVElDRSAgUC5OQVZFRU4gUkFPZGQCBg9kFgJmDw8WBB8EBSxDb3JhbTogVEhFIEhPTj9CTEUgU1JJIEpVU1RJQ0UgIFAuTkFWRUVOIFJBTx8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0MjhkZAIHDw8WAh8EBQozMC8xMi8yMDE0ZGQCCA8PFgIfBAUHanVzdGljZWRkAg8PZBYSZg8PFgIfBAUFMTI0NTNkZAIBDw8WAh8EBQIxNGRkAgIPDxYCHwQFHVdSSVQgUEVUSVRJT04gTm8uNjQ4NCBPRiAyMDA5ZGQCAw9kFgJmDw8WAh8EBTxDaC4gSmFpIFNoYW5rYXIgVnMuIFRoZSBDb21taXNzaW9uZXIgb2YgRW5kb3dtZW50cywgR292ZXJubWVkZAIED2QWAmYPDxYEHwQFPENoLiBKYWkgU2hhbmthciBWcy4gVGhlIENvbW1pc3Npb25lciBvZiBFbmRvd21lbnRzLCBHb3Zlcm5tZR8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0NTNkZAIFD2QWAmYPDxYCHwQFLUNvcmFtOiBIT04%2FQkxFIFNSSSBKVVNUSUNFIENIQUxMQSBLT0RBTkRBIFJBTWRkAgYPZBYCZg8PFgQfBAUtQ29yYW06IEhPTj9CTEUgU1JJIEpVU1RJQ0UgQ0hBTExBIEtPREFOREEgUkFNHwUFG3FyeWRpc3AuYXNweD9maWxlbmFtZT0xMjQ1M2RkAgcPDxYCHwQFCjMwLzEyLzIwMTRkZAIIDw8WAh8EBQdqdXN0aWNlZGQCEA9kFhJmDw8WAh8EBQUxMjQ2NGRkAgEPDxYCHwQFAjE1ZGQCAg8PFgIfBAUeV1JJVCBQRVRJVElPTiBOby4gNDAxNTEgb2YyMDE0ZGQCAw9kFgJmDw8WAh8EBVFWYWRsYWtvbmRhU2F0ZWVzaCwgIEthcmltbmFnYXIgRGlzdHJpIFZzLiBUaGUgU3RhdGUgb2ZUZWxhbmdhbmEsUmVwLiBieSBpdHMgUHJpbmNkZAIED2QWAmYPDxYEHwQFUVZhZGxha29uZGFTYXRlZXNoLCAgS2FyaW1uYWdhciBEaXN0cmkgVnMuIFRoZSBTdGF0ZSBvZlRlbGFuZ2FuYSxSZXAuIGJ5IGl0cyBQcmluYx8FBRtxcnlkaXNwLmFzcHg%2FZmlsZW5hbWU9MTI0NjRkZAIFD2QWAmYPDxYCHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBUSEVDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEFBTkQgVEgsIFJkZAIGD2QWAmYPDxYEHwQFRkNvcmFtOiBUSEUgSE9OP0JMRSBUSEVDSElFRiBKVVNUSUNFIFNSSSBLQUxZQU4gSllPVEkgU0VOR1VQVEFBTkQgVEgsIFIfBQUbcXJ5ZGlzcC5hc3B4P2ZpbGVuYW1lPTEyNDY0ZGQCBw8PFgIfBAUKMzAvMTIvMjAxNGRkAggPDxYCHwQFB2p1c3RpY2VkZAIGDw8WBB8EBRYxMzk0IFJlY29yZHMgcmV0cmlldmVkHgdWaXNpYmxlZ2RkAgoPDxYCHwQFG1NlYXJjaGVkIGZvciBKdWRnZToganVzdGljZWRkAgwPDxYCHwQFCSVqdXN0aWNlJWRkZMKtwI8JRYTEzGQJfmCD0HA%2Fne1p&__VIEWSTATEGENERATOR=DBA9DB93&__EVENTVALIDATION=%2FwEWGgKx8sSFBAK%2B97KkAQK%2F97KkAQLA97KkAQK597KkAQK697KkAQK797KkAQK897KkAQK197KkAQK297KkAQK998b%2FCQK%2B98b%2FCQK%2F98b%2FCQLA98b%2FCQK598b%2FCQK698b%2FCQK798b%2FCQK898b%2FCQK198b%2FCQK298b%2FCQK994ruDwK%2B94ruDwK%2F94ruDwLA94ruDwK594ruDwK694ruD3NAkBlsDV%2FiPE68scbXvxnH4YnG";
                                userAgent.sendPOST("http://judis.nic.in/judis_andhra/judseq.aspx", requestMessage);
                                String datap = userAgent.doc.innerXML();
                                System.out.println("DOCUMENT:\n" + datap);
                                index = foundAt+1;
                                count++;
                                System.out.print(count);
                            }
                            System.out.print("GFg");
                            if(data.substring(foundAt + 53, foundAt + 55).contains("25") ||data.substring(foundAt + 53, foundAt + 55).contains("26" ))
                             continue;
                            else
                                break;
                        }
                    }
                   }

                //Himanchal Pradesh   
                 else if (Links[iterator] == "http://164.100.138.36/casest/order/date-query.php") {
                    //order
                    
                    String requestMessage = "date5=" + yearFrom + "-" + monthFrom + "-" + dayFrom + "&date5_dp=1&date5_year_start=2000&date5_year_end=2015&date5_da1=946665000&date5_da2=1451500200&date5_sna=1&date5_aut=&date5_frm=&date5_tar=&date5_inp=&date5_fmt=j+F+Y&date5_dis=&date5_pr1=&date5_pr2=&date5_prv=&date5_pth=calendar%2F&date5_spd=%5B%5B%5D%2C%5B%5D%2C%5B%5D%5D&date5_spt=0&date5_och=&date5_str=0&date5_rtl=&date5_wks=&date5_int=1&date5_hid=1&date5_hdt=1000&date6=" + yearPresent + "-" + monthPresent + "-" + dayPresent + "&date6_dp=1&date6_year_start=2000&date6_year_end=2015&date6_da1=946665000&date6_da2=1451500200&date6_sna=1&date6_aut=&date6_frm=&date6_tar=&date6_inp=&date6_fmt=j+F+Y&date6_dis=&date6_pr1=&date6_pr2=&date6_prv=&date6_pth=calendar%2F&date6_spd=%5B%5B%5D%2C%5B%5D%2C%5B%5D%5D&date6_spt=0&date6_och=&date6_str=0&date6_rtl=&date6_wks=&date6_int=1&date6_hid=1&date6_hdt=1000&order=O&bench=all";
                    System.out.println(requestMessage);
                    userAgent.sendPOST("http://164.100.138.36/casest/order/date-order-judgment.php", requestMessage);
                    String data = userAgent.doc.innerXML();
                    System.out.println("DOCUMENT:\n" + data);
                    
                    //judgement
                    requestMessage = "date5=" + yearFrom + "-" + monthFrom + "-" + dayFrom + "&date5_dp=1&date5_year_start=2000&date5_year_end=2015&date5_da1=946665000&date5_da2=1451500200&date5_sna=1&date5_aut=&date5_frm=&date5_tar=&date5_inp=&date5_fmt=j+F+Y&date5_dis=&date5_pr1=&date5_pr2=&date5_prv=&date5_pth=calendar%2F&date5_spd=%5B%5B%5D%2C%5B%5D%2C%5B%5D%5D&date5_spt=0&date5_och=&date5_str=0&date5_rtl=&date5_wks=&date5_int=1&date5_hid=1&date5_hdt=1000&date6=" + yearPresent + "-" + monthPresent + "-" + dayPresent + "&date6_dp=1&date6_year_start=2000&date6_year_end=2015&date6_da1=946665000&date6_da2=1451500200&date6_sna=1&date6_aut=&date6_frm=&date6_tar=&date6_inp=&date6_fmt=j+F+Y&date6_dis=&date6_pr1=&date6_pr2=&date6_prv=&date6_pth=calendar%2F&date6_spd=%5B%5B%5D%2C%5B%5D%2C%5B%5D%5D&date6_spt=0&date6_och=&date6_str=0&date6_rtl=&date6_wks=&date6_int=1&date6_hid=1&date6_hdt=1000&order=J&bench=all";
                    userAgent.sendPOST("http://164.100.138.36/casest/order/date-query.php", requestMessage);
                    data = userAgent.doc.innerXML();
                    System.out.println("DOCUMENT:\n" + data);
                } 
                


                //Jharkhand
                else if (Links[iterator] == "http://jhr.nic.in/hcjudge/date_wise.php") {
                    int pageNo = 1;
                    // Query Format is Eg. http://jhr.nic.in/hcjudge/date_output.php?page=2&from=2014/1/1&to=2015/1/1
                    String Query = "http://jhr.nic.in/hcjudge/date_output.php?page="+pageNo+"&from=" +yearFrom+"/"+monthFrom+"/"+dayFrom+"&to="+yearPresent+"/"+monthPresent+"/"+dayPresent;
                    userAgent.visit(Query);
                    String data = userAgent.doc.innerXML();
                    System.out.println("DOCUMENT:\n" + data);
                    if( data.contains("Record Not Found!"))
                        ;//error statemnt
                    else
                    {
                        System.out.println("ASDFGHJKL");
                         int index = data.indexOf("Total no. of records found :-");
                         System.out.println(index);
                         System.out.println(data.charAt(index+29));
                         System.out.println(data.charAt(data.indexOf("<br",index+27)));
                         int records = Integer.parseInt(data.substring(index+29, data.indexOf("<br",index+27)));
                         System.out.println(records);
                         int limitPage = (records %10 ==0)?records/10:(records/10)+1;
                         System.out.println(limitPage);
                         /*for( int i = 2; i <= limitPage; i++)
                                 {
                                     Query = "http://jhr.nic.in/hcjudge/date_output.php?page="+i+"&from=" +yearFrom+"/"+monthFrom+"/"+dayFrom+"&to="+yearPresent+"/"+monthPresent+"/"+dayPresent;
                                     userAgent.visit(Query);
                                     data = userAgent.doc.innerXML();
                                     System.out.println("DOCUMENT:\n" + data);
                                 
                                 }*/
                    }
                } 


                // Madhya Pradesh (Gwalior / Jabalpur / Indore)   
                else if (Links[iterator] == "http://www.mphc.in/?q=node/129&id=GWL"
                        || Links[iterator] == "http://www.mphc.in/?q=node/129&id=IND"
                        || Links[iterator] == "http://www.mphc.in/?q=node/129&id=JBP")
                 
                 {
                    String court = "";//"GWL/IND/JBP";
                    String judge = "";//from the website
                    String requestMessage = "opt1=2&court=" + court + "&lst_case=&txtno=&txtyear=2015&lst_judge=" + judge + "&lst_pet=&txtparty=&lst_counsel=&txtcounsel=&date1=" + yearFrom + "-" + monthFrom + "-" + dayFrom + "&date2=" + yearPresent + "-" + monthPresent + "-" + dayPresent + "&submit=Submit";

                    userAgent.sendPOST("http://www.mphc.in/?q=node/129&id=GWL", requestMessage);
                    String data = userAgent.doc.innerXML();
                    System.out.println("DOCUMENT:\n" + data);
                    if( data.contains("No Jugdement or Order found that you want to search"))
                        ;//error
                    else
                        ;//send the data
                } 
                
                //Orissa
                //Uttarakhand
                else if (Links[iterator] == "http://lobis.nic.in/ori/juddt.php?scode=19"
                        || Links[iterator] == "http://lobis.nic.in/uhc/juddt.php?scode=35") {
                    System.out.println("FGFG");
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    Date startDate = null, endDate = null;
                    String date1 = dayFrom + "-" + monthFrom + "-" + yearFrom;
                    String date2 = dayPresent + "-" + monthPresent + "-" + yearPresent;
                    try {
                        startDate = dateFormat.parse(date1); // or 2010-05-01
                        endDate = dateFormat.parse(date2); // or 2010-06-01
                    } 
                    catch (ParseException pe) {
                        System.exit(-1);
                    }
                    Calendar start = Calendar.getInstance();
                    start.setTime(startDate);
                    Calendar end = Calendar.getInstance();
                    end.setTime(endDate);
                    while(!start.after(end)) 
                    {
                        int year = start.get(Calendar.YEAR);
                        int month = start.get(Calendar.MONTH) + 1;
                        int day = start.get(Calendar.DAY_OF_MONTH);
                        System.out.printf("%d.%d.%d\n", day, month, year);        
                        start.add(Calendar.DATE, 1);
                    
                    
                        String requestMessage = "juddt=" + day + "%2F" + month + "%2F" + year + "&Submit=Submit";
                        userAgent.sendPOST("http://www.mphc.in/?q=node/129&id=GWL", requestMessage);
                        String data = userAgent.doc.innerXML();
                        System.out.println("DOCUMENT:\n" + data);
                        if(data.contains("No Judgments found! Please Try Again!!!"))
                            ;//send error
                        else
                            ;
                            
                    } 
                }
                
               // System.out.println("cvvb");
                //delhi
                else if(Links[iterator].contains("http://lobis.nic.in/dhc/juddt.php?scode=31") )
                {
                    System.out.println("FDFDF");
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    Date startDate = null, endDate = null;
                    String date1 = dayFrom + "-" + monthFrom + "-" + yearFrom;
                    String date2 = dayPresent + "-" + monthPresent + "-" + yearPresent;
                    try {
                        startDate = dateFormat.parse(date1); // or 2010-05-01
                        endDate = dateFormat.parse(date2); // or 2010-06-01
                    } 
                    catch (ParseException pe) {
                        System.exit(-1);
                    }
                    Calendar start = Calendar.getInstance();
                    start.setTime(startDate);
                    Calendar end = Calendar.getInstance();
                    end.setTime(endDate);
                    while(!start.after(end)) 
                    {
                        int year = start.get(Calendar.YEAR);
                        int month = start.get(Calendar.MONTH) + 1;
                        int day = start.get(Calendar.DAY_OF_MONTH);
                        System.out.printf("%d.%d.%d\n", day, month, year);        
                        start.add(Calendar.DATE, 1);
                    
                    
                        String requestMessage = "juddt=" + day + "%2F" + month + "%2F" + year + "&Submit=Submit";
                        userAgent.sendPOST("http://lobis.nic.in//dhc/juddt1.php?dc=31&%20fflag=1", requestMessage);
                        String data = userAgent.doc.innerXML();
                        //System.out.println("DOCUMENT:\n" + data);
                        if(data.contains("NO ROWS!!! TRY AGAIN!!")||data.contains("INVALID SESSION DATE!!! TRY AGAIN!!"))
                            ;//send error
                        else
                            System.out.println("DOCUMENT:\n" + data);
                            
                    } 
                    
                }
                

                //http://dspace.judis.nic.in/simple-search?query=justice&submit=Go
                else if(Links[iterator].contains( "http://dspace.judis.nic.in")) {
                     userAgent.visit("http://dspace.judis.nic.in");
                    String query =  "http://dspace.judis.nic.in/handle/123456789/31811/browse?type=datejudgement&sort_by=2&order=DESC&rpp=100&etal=-1&null=&offset=100000";
                    String query2 = "http://dspace.judis.nic.in/handle/123456789/44524/browse?type=datejudgement&sort_by=2&order=DESC&rpp=100&etal=-1&null=&offset=100";
                    String query3 = "http://dspace.judis.nic.in/handle/123456789/44683/browse?type=datejudgement&sort_by=2&order=DESC&rpp=100&etal=-1&null=&offset=100";
                    String dateFrom = dayFrom + "-" + monthFrom + "-" + yearFrom;
                    userAgent.visit(query);
                    String data = userAgent.doc.innerXML();
                    Element table = userAgent.doc.findFirst("<table align=\"center\" class=\"miscTable\" summary=\"This table browses all dspace content\">");  //find table element
                    Element table2 = table.findEach("<tr class=\"evenRowEvenCol\">");
                    Elements tds = table2.findEach("<td headers=\"t1\">"); 
                    Dictionary dict = new Hashtable();
                    dict.put("Jan", "01");
                    dict.put("Feb", "02");
                    dict.put("Mar", "03");
                    dict.put("Apr", "04");
                    dict.put("May", "05");
                    dict.put("Jun", "06");
                    dict.put("Jul", "07");
                    dict.put("Aug", "08");
                    dict.put("Sep", "09");
                    dict.put("Oct", "10");
                    dict.put("Nov", "11");
                    dict.put("Dec", "12");
                    
                    for(Element td: tds){
                        System.out.println(td.innerText()); 
                        String date = td.innerText().replaceAll(td.innerText().substring(td.innerText().indexOf("-")+1,td.innerText().indexOf("-")+4), (String)dict.get(td.innerText().substring(td.innerText().indexOf("-")+1,td.innerText().indexOf("-")+4)));
                        System.out.println(date); //d(d)-mm-year
                        int count = 1;
                        try{
 
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            Date date1 = sdf.parse(date);
                            Date date2 = sdf.parse(dateFrom);
                            System.out.println(sdf.format(date1));
                            System.out.println(sdf.format(date2));
                            if(date1.compareTo(date2)<0){
                                break; 
                            }
                        }
                        catch(ParseException ex){
                            ex.printStackTrace();}
                        count++;
                    }
                    //send data//http://dspace.judis.nic.in/handle/123456789/31811/browse?type=datejudgement&sort_by=2&order=DESC&rpp=100&etal=-1&null=&offset=count
                }
                
                
                
                else if(Links[iterator].contains( "http://courtnic.nic.in/jodh/dojqry.asp"))
                {
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    Date startDate = null, endDate = null;
                    String date1 = dayFrom + "-" + monthFrom + "-" + yearFrom;
                    String date2 = dayPresent + "-" + monthPresent + "-" + yearPresent;
                    try {
                        startDate = dateFormat.parse(date1); // or 2010-05-01
                        endDate = dateFormat.parse(date2); // or 2010-06-01
                    } 
                    catch (ParseException pe) {
                        System.exit(-1);
                    }
                    Calendar start = Calendar.getInstance();
                    start.setTime(startDate);
                    Calendar end = Calendar.getInstance();
                    end.setTime(endDate);
                    while(!start.after(end)) 
                    {
                        int year = start.get(Calendar.YEAR);
                        int month = start.get(Calendar.MONTH) + 1;
                        int day = start.get(Calendar.DAY_OF_MONTH);
                        System.out.printf("%d.%d.%d\n", day, month, year);        
                        start.add(Calendar.DATE, 1);
                    
                    
                        String requestMessage = "juddt=" + day + "%2F" + month + "%2F" + year + "&Submit=Submit";
                        //String query = "http://courtnic.nic.in//jodh/dojqry.asp?datef=2015-01-01&selfday=01&selfmonth=01&selfyear=2015&B1=Search";
                        String query = "http://courtnic.nic.in//jodh/dojqry.asp?datef="+year+"-"+month+"-"+day+"&selfday="+day+"&selfmonth="+month+"&selfyear="+year+"&B1=Search";
                        userAgent.visit(query);
                        String data = userAgent.doc.innerXML();
                        System.out.println("DOCUMENT:\n" + data);
                        if(data.contains("No records found!"))
                            ;//send error
                        else
                            ;//
                            
                    }
                   
                }
                
                
                
                else if(Links[iterator].contains( "http://rhccasestatus.raj.nic.in/smsrhcb/rhbcis/dojqry.asp"))
                {
                    int pageNo = 1;
                    //String query = "http://rhccasestatus.raj.nic.in/smsrhcb/rhbcis/qrydoj.asp?fdt=1950-01-01&tdt=2015-02-01&page=18&order="
                    String query = "http://rhccasestatus.raj.nic.in/smsrhcb/rhbcis/qrydoj.asp?fdt="+yearFrom+"-"+monthFrom+"-"+dayFrom+"&tdt="+yearPresent+"-"+monthPresent+"-"+dayPresent+"&page="+ pageNo+"&order=";
                    userAgent.visit(query);
                    String data2 = userAgent.doc.innerXML();
                    System.out.println("DOCUMENT:\n" + data2);
                    String data ;
                    data = data2.replaceAll("\\s+","");
                    if( data.contains("No records found!"))
                        ;//error
                    else
                    {
                        int index =  data.indexOf("Page<strong>1</strong>of<strong>");
                        int pages = Integer.parseInt(data.substring(data.indexOf("<strong>",index+23)+8,data.indexOf("</strong>",index+23)));
                        for (pageNo = 1; pageNo <= pages; pageNo++)
                        {
                            query = "http://rhccasestatus.raj.nic.in/smsrhcb/rhbcis/qrydoj.asp?fdt="+yearFrom+"-"+monthFrom+"-"+dayFrom+"&tdt="+yearPresent+"-"+monthPresent+"-"+dayPresent+"&page="+ pageNo+"&order=";
                            userAgent.visit(query);
                            data = userAgent.doc.innerXML();
                            System.out.println("DOCUMENT:\n" + data);
                        }
                    }
                }
            
        }
    }
            
        catch (JauntException e) {   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
            System.err.println(e);
        }
    }

}
