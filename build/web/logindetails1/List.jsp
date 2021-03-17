<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Logindetails Items</title>
            <link rel="stylesheet" type="text/css" href="/login/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Logindetails Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Logindetails Items Found)<br />" rendered="#{logindetails.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{logindetails.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{logindetails.pagingInfo.firstItem + 1}..#{logindetails.pagingInfo.lastItem} of #{logindetails.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{logindetails.prev}" value="Previous #{logindetails.pagingInfo.batchSize}" rendered="#{logindetails.pagingInfo.firstItem >= logindetails.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{logindetails.next}" value="Next #{logindetails.pagingInfo.batchSize}" rendered="#{logindetails.pagingInfo.lastItem + logindetails.pagingInfo.batchSize <= logindetails.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{logindetails.next}" value="Remaining #{logindetails.pagingInfo.itemCount - logindetails.pagingInfo.lastItem}"
                                   rendered="#{logindetails.pagingInfo.lastItem < logindetails.pagingInfo.itemCount && logindetails.pagingInfo.lastItem + logindetails.pagingInfo.batchSize > logindetails.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{logindetails.logindetailsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Userid"/>
                            </f:facet>
                            <h:outputText value="#{item.userid}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Username"/>
                            </f:facet>
                            <h:outputText value="#{item.username}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Password"/>
                            </f:facet>
                            <h:outputText value="#{item.password}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Firstname"/>
                            </f:facet>
                            <h:outputText value="#{item.firstname}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Lastname"/>
                            </f:facet>
                            <h:outputText value="#{item.lastname}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Emailaddress"/>
                            </f:facet>
                            <h:outputText value="#{item.emailaddress}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{logindetails.detailSetup}">
                                <f:param name="jsfcrud.currentLogindetails" value="#{jsfcrud_class['register.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][logindetails.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{logindetails.editSetup}">
                                <f:param name="jsfcrud.currentLogindetails" value="#{jsfcrud_class['register.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][logindetails.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{logindetails.remove}">
                                <f:param name="jsfcrud.currentLogindetails" value="#{jsfcrud_class['register.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][logindetails.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{logindetails.createSetup}" value="New Logindetails"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
