
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />

  </head>
  <body>
  <g:render template="/template/adminMenu" />
  <div class="body">
    <h1>Summary</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
      <table>
        <thead>
          <tr>
            <td>Site Id  </td>
            <td>App Name </td>
            <td>Platform </td>
            <td>App Status </td>
            <td>Site Category </td>
            <td>Url </td>
            <td>View </td>
          </tr>
        </thead>
        <tbody>
        <g:each in="${resList}" status="i" var="res">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td>${res.siteId}</td>
            <td>${res.appName}</td>
            <td>${res.platform}</td>
            <td>${res.appStatus}</td>
            <td>${res.siteCategory}</td>
            <td>${res.url}</td>

            <td><g:link controller="admin" action="viewDetails">View Reports</g:link></td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${resList.size()}" />
    </div>
  </div>
</body>
</html>
