<%@page import="org.springframework.security.core.context.*"%>
<%@page import="org.springframework.security.core.userdetails.*"%>
<%@page import="org.springframework.security.core.session.SessionRegistry" %>
<%@page import="java.util.*"%>
<%@page import="org.games.onlie.player.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>
		<output>Hello welcome to games online</output>
	</h1>

	<c:choose>
		<c:when test="${username=='anonymousUser' }">
			<c:out value="you are not logged in"/>
				<a href="OnlineGames/spring_security_login">log in</a>
		</c:when>
		<c:otherwise>	
			<c:out value="hello ${username}"/>
			log out
		</c:otherwise>
	</c:choose>
	<P>The time on the server is ${serverTime}.</P>
	
	<%
		Object factory = request.getAttribute("factory");
		out.println(factory.getClass());
	%>
	
</body>
</html>
