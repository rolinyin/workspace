package rpcserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {
	
	Socket socket;
	
	Object service;
	
	public ProcessorHandler(Socket socket, Object service){
		this.socket = socket;
		this.service = service;
	}

	public void run() {
		System.out.println("开始处理客户端请求");
		ObjectInputStream inputStream = null;
		try {
			inputStream =new ObjectInputStream(socket.getInputStream());
			RPCRequest rpcRequest = (RPCRequest) inputStream.readObject();
			Object result = invoke(rpcRequest);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(result);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Object invoke(RPCRequest request){
		Object[] args = request.getParameters();
		Class<?>[] types = new Class[args.length];
		for(int i = 0, length = args.length; i < length;i++){
			types[i] = args[i].getClass();
		}
		try {
			Method method = service.getClass().getMethod(request.getMethodName(), types);
			return method.invoke(service, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
