package com.lzt.operate.codetools.app.listeners;

import com.lzt.operate.codetools.app.services.CustomApplicationInit;
import com.lzt.operate.utility.listeners.BaseCustomApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.stereotype.Component;

/**
 * @author lzt
 */
@Component
public class CustomApplicationListener extends BaseCustomApplicationListener<CustomApplicationInit> {

	protected CustomApplicationListener() {
		super(CustomApplicationInit.class);
	}

	@Override
	protected void handleApplicationStarting(ApplicationStartingEvent applicationEvent) {
	}

	@Override
	protected void handleApplicationEnvironmentPrepared(ApplicationEnvironmentPreparedEvent applicationEvent) {
	}

	@Override
	protected void handleApplicationPrepared(ApplicationPreparedEvent applicationEvent) {
	}

	@Override
	protected void handleApplicationStarted(ApplicationStartedEvent applicationEvent) {
	}

	@Override
	protected void handleApplicationReadyOther(ApplicationReadyEvent applicationEvent) {
	}

	@Override
	protected void handleApplicationFailed(ApplicationFailedEvent applicationEvent) {
	}
}
