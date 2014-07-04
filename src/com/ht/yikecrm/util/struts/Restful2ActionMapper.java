package com.ht.yikecrm.util.struts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.RequestUtils;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.mapper.DefaultActionMapper;

import com.opensymphony.xwork2.config.ConfigurationManager;

/**
 * override the struts2 Restful2ActionMapper for rest url and normal url.
 * 
 * @author zb
 * 
 */
public class Restful2ActionMapper extends DefaultActionMapper {
	protected static final Log LOG = LogFactory.getLog(Restful2ActionMapper.class);

	public static final String HTTP_METHOD_PARAM = "__http_method";
	private static final byte HTTP_METHOD_GET = 1;
	private static final byte HTTP_METHOD_POST = 2;
	private static final byte HTTP_METHOD_PUT = 3;
	private static final byte HTTP_METHOD_DELETE = 4;
	private static final String splitChar = "/";

	List extensions = new ArrayList() {
		{
			add(".yk/");
		}
	};

	public Restful2ActionMapper() {
		setSlashesInActionNames("true");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.dispatcher.mapper.ActionMapper#getMapping(javax.servlet.http.HttpServletRequest)
	 */
	public ActionMapping getMapping(HttpServletRequest request,
			ConfigurationManager configManager) {

		ActionMapping mapping = super.getMapping(request, configManager);

		if (mapping != null) {
			LOG.debug("=============namespace============:"
					+ mapping.getNamespace());
			LOG.debug("=============name============:" + mapping.getName());
		}

		String uri = (String) request
				.getAttribute("javax.servlet.include.servlet_path");
		if (uri == null) {
			uri = RequestUtils.getServletPath(request);
			LOG.debug("=============uri============:" + uri);
			if (uri == null || "".equals(uri)) {
				uri = request.getRequestURI();
				uri = uri.substring(request.getContextPath().length());
			}
		}

		LOG.debug("=============uri============:" + uri);

		if (extensions != null) {
			Iterator it = extensions.iterator();
			boolean doRestFlag = false;
			String[] tempStrings = null;

			while (it.hasNext()) {
				String extension = (String) it.next();
				if (uri.contains(extension) && !uri.contains("!")) {

					tempStrings = uri.split(extension);

					doRestFlag = true;

					break;
				}
			}

			if (doRestFlag) {

				String[] temp = tempStrings[0].split(splitChar); // namespace&name
				if (temp.length != 2) {
					return mapping;
				}

				if (mapping == null) {
					mapping = new ActionMapping();
				}

				if (mapping.getMethod() != null) {
					return mapping;
				}
				
				LOG.debug("=============namespace============:"
						+ mapping.getNamespace());
				LOG.debug("=============name============:" + mapping.getName());

				mapping.setNamespace(splitChar);
				mapping.setName(temp[1]);

				LOG.debug("=============namespace============:"
						+ mapping.getNamespace());
				LOG.debug("=============name============:" + mapping.getName());

				String actionName = mapping.getName();
				if ((actionName == null) || (actionName.length() == 0))
					return mapping;

				// If a method hasn't been explicitly named, try to guess
				// using
				// ReST-style patterns
				if (tempStrings.length == 2) {
					request.setAttribute("tableId", tempStrings[1]);
				}

				String requestMethod = request.getMethod();
				String httpMethodParam = request
						.getParameter(HTTP_METHOD_PARAM);
				byte requestMethodCode = 0;
				if ("PUT".equalsIgnoreCase(requestMethod))
					requestMethodCode = HTTP_METHOD_PUT;
				else if ("DELETE".equalsIgnoreCase(requestMethod))
					requestMethodCode = HTTP_METHOD_DELETE;
				else if ("PUT".equalsIgnoreCase(httpMethodParam))
					requestMethodCode = HTTP_METHOD_PUT;
				else if ("DELETE".equalsIgnoreCase(httpMethodParam))
					requestMethodCode = HTTP_METHOD_DELETE;
				else if ("GET".equalsIgnoreCase(requestMethod))
					requestMethodCode = HTTP_METHOD_GET;
				else if ("POST".equalsIgnoreCase(requestMethod))
					requestMethodCode = HTTP_METHOD_POST;

				if (requestMethodCode == HTTP_METHOD_GET) {
					mapping.setMethod("query");
				} else if (requestMethodCode == HTTP_METHOD_POST)// insert
					mapping.setMethod("insert");
				else if (requestMethodCode == HTTP_METHOD_PUT)// update
					mapping.setMethod("update");
				else if (requestMethodCode == HTTP_METHOD_DELETE)
					mapping.setMethod("delete");

			}

		}
		return mapping;
	}
}
