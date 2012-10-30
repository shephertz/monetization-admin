<!--
 Shephertz Technologies
 Author  Jatin
 Date  12 March 2011
 Version 0.1
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<div >
  <div class="nav">
    <span class="menuButton"><g:link controller="admin"><g:message code="default.home.label"/></g:link></span>
    <span class="menuButton"><g:link controller="admin" action="summary">Summary </g:link></span>
    <span class="menuButton"><g:link controller="admin" action="list">Users</g:link></span>

  </div>
  <div class="nav" id="moreContent" style="display:none;">
    <span class="menuButton"><g:link controller="designBulkUpload" action="index"  >Design Bulk Upload</g:link></span>
    <span class="menuButton"><g:link controller="projectImages" action="list" >Project Images</g:link></span>
    <span class="menuButton"><g:link controller="footerManagement" action="list" >Footer Mgmt</g:link></span>
    <span class="menuButton"><g:link controller="feelingTheLove" action="list"  >Feeling The Love</g:link></span>

    <span class="menuButton"><g:link controller="sellAnnouncement" action="list"  >Sell Announcement</g:link></span>
    <span class="menuButton"><g:link controller="quartz" action="list"  >Jobs Mgmt</g:link></span>
  </div>


</div>
<g:if test="${flash.message}">
  <h2 style="color:red">
${flash.message}</h2>
</g:if>