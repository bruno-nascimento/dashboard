package br.com.bsitecnologia.dashboard.infra.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ExcludeJsfResourcesFromFilter {
	
	private static Pattern excludeUrls = Pattern.compile("^.*/(css|js|images|javax.faces.resource)/.*$", Pattern.CASE_INSENSITIVE);
	
	public static boolean shouldProcess(ServletRequest request){
       String url = ((HttpServletRequest)request).getRequestURI().toString();
       Matcher m = excludeUrls.matcher(url);
       return (!m.matches());
	}


}
