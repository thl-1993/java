package edu.school21;


import edu.school21.interfaces.Printer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!");
        context.close();
    }


}
