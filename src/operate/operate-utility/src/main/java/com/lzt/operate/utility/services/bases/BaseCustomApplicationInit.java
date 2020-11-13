package com.lzt.operate.utility.services.bases;

/**
 * 应用程序初始化基类,系统初始化后执行一些业务操作
 *
 * @author 卢志涛
 */
public abstract class BaseCustomApplicationInit {

	/**
	 * 应用初始化后，进行一些业务操作，如启动某些工作线程，初始化系统某些参数
	 */
	public abstract void init();

}
