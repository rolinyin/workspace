package rpcserver;

import java.io.Serializable;

public class RPCRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7966032440119027303L;
	private String className;
	private String methodName;
	private Object[] parameters;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
}
