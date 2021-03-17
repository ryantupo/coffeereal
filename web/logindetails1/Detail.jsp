<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Logindetails Detail</title>
            <link rel="stylesheet" type="text/css" href="/login/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Logindetails Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Userid:"/>
                    <h:outputText value="#{logindetails.logindetails.userid}" title="Userid" />
                    <h:outputText value="Username:"/>
                    <h:outputText value="#{logindetails.logindetails.username}" title="Username" />
                    <h:outputText value="Password:"/>
                    <h:outputText value="#{logindetails.logindetails.password}" title="Password" />
                    <h:outputText value="Firstname:"/>
                    <h:outputText value="#{logindetails.logindetails.firstname}" title="Firstname" />
                    <h:outputText value="Lastname:"/>
                    <h:outputText value="#{logindetails.logindetails.lastname}" title="Lastname" />
                    <h:outputText value="Emailaddress:"/>
                    <h:outputText value="#{logindetails.logindetails.emailaddress}" title="Emailaddress" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{logindetails.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentLogindetails" value="#{jsfcrud_class['register.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][logindetails.logindetails][logindetails.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{logindetails.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentLogindetails" value="#{jsfcrud_class['register.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][logindetails.logindetails][logindetails.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{logindetails.createSetup}" value="New Logindetails" />
                <br />
                <h:commandLink action="#{logindetails.listSetup}" value="Show All Logindetails Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
