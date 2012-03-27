<%@page import="org.springframework.security.core.context.*" %>
<%@page import="org.springframework.security.core.userdetails.*" %>
<html>
<head>
<title>my game</title>
</head>
<body>
	game applet
	<%
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
		  String username = ((UserDetails)principal).getUsername();
		  out.println(username);
		} else {
		  String username = principal.toString();
		}
	%>
</body>
</html>