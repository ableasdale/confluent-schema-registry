import io.confluent.developer.avro.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {
        Purchase p = new Purchase("x",1.23,"23");
        LOG.info("Schema: "+p.getSchema());
    }
}
