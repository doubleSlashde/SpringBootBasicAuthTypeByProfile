package de.doubleslash.example.springboot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginAttemptsLogger {

	private static final Logger LOG = LoggerFactory.getLogger(LoginAttemptsLogger.class);

	@EventListener
	public void auditEventHappened(AuditApplicationEvent auditApplicationEvent) {

		AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
		
		LOG.debug("Principal {} - {} ", auditEvent.getPrincipal(), auditEvent.getType());

		WebAuthenticationDetails details = (WebAuthenticationDetails) auditEvent.getData().get("details");

		LOG.debug("  Remote IP address: {}, session Id: {}", details.getRemoteAddress(), details.getSessionId());
	}
}