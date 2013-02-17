package br.com.bsitecnologia.dashboard.infra.filters.doctype;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import br.com.bsitecnologia.dashboard.infra.filters.ExcludeJsfResourcesFromFilter;

@WebFilter(filterName="DocTypeFilter", dispatcherTypes=DispatcherType.REQUEST, urlPatterns="*.jsf")
public class DocTypeFilter implements Filter {

	public FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getContentType() == null && ExcludeJsfResourcesFromFilter.shouldProcess(request)){
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
			chain.doFilter(request, wrapper);
			String modifiedHtml = "<!DOCTYPE html>"+wrapper.toString();
			response.setContentLength(modifiedHtml.getBytes().length);
			out.write(modifiedHtml);
			out.close();
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
