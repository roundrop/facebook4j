package facebook4j;

import org.junit.Test;

public class JavaEnvTest {

    @Test
    public void printEnv() {
        System.out.println("================================");
        System.out.println(Runtime.class.getPackage().getImplementationVersion());
        System.out.println("================================");
        System.out.println("java.version=" + System.getProperty("java.version"));
        System.out.println("java.specification.version=" + System.getProperty("java.specification.version"));
        System.out.println("java.vm.version=" + System.getProperty("java.vm.version"));
        System.out.println("java.class.version=" + System.getProperty("java.class.version"));
        System.out.println("================================");
    }


}
