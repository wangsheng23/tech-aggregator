/**
 * 
 */
package server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Configure Jetty on XML
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class XmlWebServer {
	
	 // TODO: You should configure this appropriately for your environment
	private static final String LOG_PATH = "D:/usr/local/var/log/aggregator.log";
	// private static final String WEB_XML = "src/main/webapp/WEB-INF/web.xml";
	private static final String PROJECT_RELATIVE_PATH_TO_WEBAPP = "src/main/webapp";
	
	private Server server;
	private int port;
	private String host;
	
	public XmlWebServer(int port) {
		this("localhost", port);
	}
	
	public XmlWebServer(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void start() throws Exception {
		server = new Server(createThreadPool());
		
		// server.setThreadPool(createThreadPool());
        server.addConnector(createConnector());
        server.setHandler(createHandlers());        
        server.setStopAtShutdown(true);

        server.start();  
	}
	
	public void join() throws Exception {
		server.join();
	}
	
	public void stop() throws Exception {
		server.stop();
	}
	
	private ThreadPool createThreadPool() {
		QueuedThreadPool _threadPool = new QueuedThreadPool();
		_threadPool.setMinThreads(10);
		_threadPool.setMaxThreads(100);
		return _threadPool;
	}
	
	private ServerConnector createConnector() {
		ServerConnector _connector = new ServerConnector(server);
		_connector.setPort(port);
		_connector.setHost(host);
		return _connector;
	}
	
	private HandlerCollection createHandlers() {
		WebAppContext _ctx = new WebAppContext();
		_ctx.setContextPath("/");
		_ctx.setResourceBase(PROJECT_RELATIVE_PATH_TO_WEBAPP);  //Ö¸¶¨webappÄ¿Â¼  
		_ctx.setDescriptor(PROJECT_RELATIVE_PATH_TO_WEBAPP + "/WEB-INF/web.xml");
		_ctx.setParentLoaderPriority(true);  
		// _ctx.setWar(PROJECT_RELATIVE_PATH_TO_WEBAPP);
		List<Handler> _handlers = new ArrayList<Handler>();
		_handlers.add(_ctx);
		
		HandlerList _contexts = new HandlerList();
		_contexts.setHandlers(_handlers.toArray(new Handler[0]));
		
		RequestLogHandler _log = new RequestLogHandler();
		_log.setRequestLog(createRequestLog());
		
		HandlerCollection _result = new HandlerCollection();
		_result.setHandlers(new Handler[]{_contexts, _log});
		
		return _result;
	}
	
	private RequestLog createRequestLog() {
		NCSARequestLog _log = new NCSARequestLog();
		File _logPath = new File(LOG_PATH);
		_logPath.getParentFile().mkdirs();
		_log.setFilename(_logPath.getPath());
		_log.setRetainDays(90);
		_log.setExtended(false);
		_log.setAppend(true);
		_log.setLogTimeZone("GMT");
		_log.setLogLatency(true);
		return _log;
	}
}
