package com.lzt.operate.utility.listeners;

import com.lzt.operate.utility.services.bases.BaseCustomApplicationInit;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

/**
 * 应用程序启动事件监听
 *
 * @author lzt
 */
public abstract class BaseCustomApplicationListener<T extends BaseCustomApplicationInit> implements ApplicationListener<ApplicationEvent> {
	private final Class<T> customApplicationInitClass;

	protected BaseCustomApplicationListener(Class<T> customApplicationInitClass) {
		this.customApplicationInitClass = customApplicationInitClass;
	}

	/**
	 * springboot应用启动且未作任何处理（除listener注册和初始化）的时候发送ApplicationStartingEvent
	 *
	 * @param applicationEvent ApplicationStartingEvent
	 */
	protected abstract void handleApplicationStarting(ApplicationStartingEvent applicationEvent);

	/**
	 * 确定springboot应用使用的Environment且context创建之前发送这个事件
	 *
	 * @param applicationEvent ApplicationEnvironmentPreparedEvent
	 */
	protected abstract void handleApplicationEnvironmentPrepared(ApplicationEnvironmentPreparedEvent applicationEvent);

	/**
	 * context已经创建且没有refresh发送个事件
	 *
	 * @param applicationEvent ApplicationPreparedEvent
	 */
	protected abstract void handleApplicationPrepared(ApplicationPreparedEvent applicationEvent);

	/**
	 * context已经refresh且application and command-line runners（如果有） 调用之前发送这个事件
	 *
	 * @param applicationEvent ApplicationStartedEvent
	 */
	protected abstract void handleApplicationStarted(ApplicationStartedEvent applicationEvent);

	private void handleApplicationReady(ApplicationReadyEvent applicationEvent) {
		handleApplicationCustomInit(applicationEvent, customApplicationInitClass);
		handleApplicationReadyOther(applicationEvent);
	}

	/**
	 * application and command-line runners （如果有）执行完后发送这个事件，此时应用已经启动完毕
	 *
	 * @param applicationEvent           ApplicationReadyEvent
	 * @param customApplicationInitClass T extends BaseCustomApplicationInit
	 */
	protected void handleApplicationCustomInit(ApplicationReadyEvent applicationEvent, Class<T> customApplicationInitClass) {
		ApplicationContext context = applicationEvent.getApplicationContext();

		T initService = context.getBean(customApplicationInitClass);

		initService.init();
	}

	/**
	 * application and command-line runners （如果有）执行完后发送这个事件，此时应用已经启动完毕
	 *
	 * @param applicationEvent ApplicationReadyEvent
	 */
	protected abstract void handleApplicationReadyOther(ApplicationReadyEvent applicationEvent);

	/**
	 * 用启动失败后产生这个事件
	 *
	 * @param applicationEvent ApplicationFailedEvent
	 */
	protected abstract void handleApplicationFailed(ApplicationFailedEvent applicationEvent);

	/**
	 * 事件处理
	 *
	 * @param applicationEvent applicationEvent
	 */
	@Override
	public void onApplicationEvent(@NonNull ApplicationEvent applicationEvent) {
		//springboot应用启动且未作任何处理（除listener注册和初始化）的时候发送ApplicationStartingEvent
		if (applicationEvent instanceof ApplicationStartingEvent) {
			handleApplicationStarting((ApplicationStartingEvent) applicationEvent);
		}

		//确定springboot应用使用的Environment且context创建之前发送这个事件
		if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
			handleApplicationEnvironmentPrepared((ApplicationEnvironmentPreparedEvent) applicationEvent);
		}

		//context已经创建且没有refresh发送个事件
		if (applicationEvent instanceof ApplicationPreparedEvent) {
			handleApplicationPrepared((ApplicationPreparedEvent) applicationEvent);
		}

		//context已经refresh且application and command-line runners（如果有） 调用之前发送这个事件
		if (applicationEvent instanceof ApplicationStartedEvent) {
			handleApplicationStarted((ApplicationStartedEvent) applicationEvent);
		}

		//application and command-line runners （如果有）执行完后发送这个事件，此时应用已经启动完毕
		if (applicationEvent instanceof ApplicationReadyEvent) {
			handleApplicationReady((ApplicationReadyEvent) applicationEvent);
		}

		//应用启动失败后产生这个事件
		if (applicationEvent instanceof ApplicationFailedEvent) {
			handleApplicationFailed((ApplicationFailedEvent) applicationEvent);
		}
	}

}
