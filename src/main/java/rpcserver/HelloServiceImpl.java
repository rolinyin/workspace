package rpcserver;

public class HelloServiceImpl implements IHelloService {

	public String sayHello(User content) {
		return "hello "+content;
	}

}
