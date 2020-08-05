// Sample.java
import org.apache.camel.builder.RouteBuilder;

public class Sample extends RouteBuilder {

   @Override
   public void configure() throws Exception {
           from("undertow:http://0.0.0.0:8080/test")
          .setBody(constant("{{env:CAMEL_SETBODY}}"))
          .log("Hello Camel-K");
   }
}