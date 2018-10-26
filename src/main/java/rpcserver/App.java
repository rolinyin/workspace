package rpcserver;

public class App {

	public static void main(String[] args){
		IHelloService helloService = new HelloServiceImpl();
		RpcServerProxy proxy = new RpcServerProxy();
		proxy.publisher(helloService, 8080);
	}
}
