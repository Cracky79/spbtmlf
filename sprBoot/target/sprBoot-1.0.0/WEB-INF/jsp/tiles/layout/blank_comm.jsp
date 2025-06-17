<%--
blank 페이지 기본 레이아웃 
Cracky 2021.05.21
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"            prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"              prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags"                 prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ko">
	<head>
		<title>:::: Logsynk - Intranet ::::</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<%@ include file = "tiles_meta.jsp" %>
	</head>
	<body class="bg-gradient-primary">
		 <tiles:insertAttribute name="page" />
	</body>
</html>


