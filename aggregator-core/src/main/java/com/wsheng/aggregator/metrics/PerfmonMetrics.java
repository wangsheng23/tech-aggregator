package com.wsheng.aggregator.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;

//import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class PerfmonMetrics {

	private static final String JAVA_VENDOR = System.getProperty("java.vendor");
	private static final boolean IS_IBM_JDK = JAVA_VENDOR.toLowerCase().contains("ibm");

	public int availableProcessors;
	public String arch;
	public String name;
	public double jvmCPU;
	public long jvmTotalMemory;
	public long jvmVirtualMemory;

	@Override
	public String toString() {
		return "PerfmonMetrics [availableProcessors=" + availableProcessors
				+ ", arch=" + arch + ", name=" + name + ", jvmCPU=" + jvmCPU
				+ ", jvmTotalMemory=" + jvmTotalMemory + ", jvmVirtualMemory="
				+ jvmVirtualMemory + "]";
	}
	
	public static PerfmonMetrics buildPerfmonMetrics( ) throws Exception {
	    OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
	    PerfmonMetrics metrics = new PerfmonMetrics();
	    metrics.availableProcessors = bean.getAvailableProcessors();
	    metrics.arch = bean.getArch();
	    metrics.name = bean.getName();

	    metrics.jvmCPU = getCPUPercent(metrics.availableProcessors, bean);
	    metrics.jvmTotalMemory = Runtime.getRuntime().totalMemory();
	    metrics.jvmVirtualMemory = (long)getVirtualMemorySize(bean);
	    return metrics;
	}

	private static Object getVirtualMemorySize(OperatingSystemMXBean bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		if(IS_IBM_JDK){
			Class<? extends OperatingSystemMXBean> clazz = bean.getClass();
			return clazz.getMethod("getProcessVirtualMemorySize").invoke(bean);
		}
		Class<?> clazz = Class.forName("com.sun.management.OperatingSystemMXBean");
		return clazz.getMethod("getCommittedVirtualMemorySize").invoke(bean);
//		return (long)clazz.getMethod("getTotalPhysicalMemorySize").invoke(bean) - (long)clazz.getMethod("getFreePhysicalMemorySize").invoke(bean);
		//"getTotalPhysicalMemorySize" is for all memory, like 8G for desktop
	}

	private static double getCPUPercent(
			int availableProcessors,
			OperatingSystemMXBean bean
		) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, InterruptedException {
		if(IS_IBM_JDK){
			Class<? extends OperatingSystemMXBean> clazz = ManagementFactory.getOperatingSystemMXBean().getClass();
			return (double)clazz.getMethod("getProcessCpuLoad").invoke(bean) * 100;
		}
		
		//Oracle
		Class<?> clazz = Class.forName("com.sun.management.OperatingSystemMXBean");
		long nanoBefore = System.nanoTime();
		long cpuBefore = (long) clazz.getMethod("getProcessCpuTime").invoke(bean);
		Thread.sleep(500);
		long cpuAfter = (long) clazz.getMethod("getProcessCpuTime").invoke(bean);
		long nanoAfter = System.nanoTime();
		long percent;
		if (nanoAfter > nanoBefore){
			percent = ((cpuAfter-cpuBefore)*100L)/(nanoAfter-nanoBefore)/availableProcessors;
		}else{
			percent = 0;
		}
		return percent;
	}
	
	
}
