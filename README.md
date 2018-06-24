# proxy-swagger-npe
POC that @Async + OpenJDK can cause an NPE in Swagger2.

The easy way to verify this is do
1. `mvn package`, this displays errors when I do it related to H2K but everything works so ....
2. `docker images`, to copy the latest container ID for the container that was build
3. `docker run < container id >`, and just wait until the error occurs.

----

So, if you run this project with an OpenJDK Java you'll notice that it'll fail due to a NPE. This occurs because the `RequestHandlerSelectors.basePackage("com.lesuorac")` check does not internally check if the package is null.
The package is only null for OpenJDK though.

If you're running this Application using Oracle's JDK then you'll see the following line
`2018-06-23 21:36:46.642 TRACE 1567 --- [           main] eConfig$$EnhancerBySpringCGLIB$$6e68140f : name: asyncCausesNpe, class: class com.sun.proxy.$Proxy73, 's package: package com.sun.proxy` Which is caused because Spring replaces the handler with a com.sun.proxy when the @Async annotation is present.
Since com.sun.proxy has a null package (as shown below) for OpenJDK this causes a NPE in the basePackage check.

If you run the following code on Try It Online for [Java](https://tio.run/#java-jdk) and [OpenJDK](https://tio.run/#java-openjdk) you can notice that the package is null for OpenJDK.
```
import java.lang.reflect.Proxy;

public class Main {

	public interface AnInterface {
	};

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		Proxy proxy = (Proxy) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { AnInterface.class },
				(p, m, a) -> {
					System.out.println(String.format("Proxy: %s, Method: %s, Args: %s", p, m, a));

					return null;
				});

		System.out.println(String.format("Proxy: %s, package: %s", proxy.getClass(), proxy.getClass().getPackage()));

	}

}
```
