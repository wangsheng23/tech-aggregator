/**
 * 
 */
package com.wsheng.aggregator.bean;

/**
 * 
 * LBMember ResourceId: ip:port
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class LBMemberSource extends LBSource {

	private static final long serialVersionUID = -3579858609342297799L;
	
	private String memberName; // ip:port - resource id

	private String ip;
	
	private String opsState;
	
	private int port;
	
	private String protocol;
	
	private String server;
	
	private String label;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOpsState() {
		return opsState;
	}

	public void setOpsState(String opsState) {
		this.opsState = opsState;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "LBMemberSource [memberName=" + memberName + ", ip=" + ip + ", opsState=" + opsState + ", port=" + port + ", protocol=" + protocol
				+ ", server=" + server + ", label=" + label + "]";
	}
	
	
	
}
