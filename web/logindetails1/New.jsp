<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Logindetails</title>
            <link rel="stylesheet" type="text/css" href="/login/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Logindetails</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{logindetails.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Username:"/>
                    <h:inputText id="username" value="#{logindetails.logindetails.username}" title="Username" required="true" requiredMessage="The username field is required." />
                    <h:outputText value="Password:"/>
                    <h:inputText id="password" value="#{logindetails.logindetails.password}" title="Password" required="true" requiredMessage="The password field is required." />
                    <h:outputText value="Firstname:"/>
                    <h:inputText id="firstname" value="#{logindetails.logindetails.firstname}" title="Firstname" required="true" requiredMessage="The firstname field is required." />
                    <h:outputText value="Lastname:"/>
                    <h:inputText id="lastname" value="#{logindetails.logindetails.lastname}" title="Lastname" required="true" requiredMessage="The lastname field is required." />
                    <h:outputText value="Emailaddress:"/>
                    <h:inputText id="emailaddress" value="#{logindetails.logindetails.emailaddress}" title="Emailaddress" required="true" requiredMessage="The emailaddress field is required." />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{logindetails.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{logindetails.listSetup}" value="Show All Logindetails Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
