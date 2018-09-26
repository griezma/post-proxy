FROM jboss/wildfly
LABEL maintainer="manfred.griesser@7id.com"
ENV deploy /opt/jboss/wildfly/standalone/deployments
ENV archive jaxrs.war
EXPOSE 8080
COPY $archive $deploy
