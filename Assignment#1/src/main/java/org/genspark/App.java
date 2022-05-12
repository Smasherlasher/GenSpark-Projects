package org.genspark;

import org.genspark.Java.AppConfig;
import org.genspark.Xml.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        xmlConfig();
        annotationConfig();
        JavaConfig();
    }
    public static void xmlConfig(){
        ApplicationContext contextXml = new ClassPathXmlApplicationContext("SpringXml.xml");
        Student studentXml = (Student) contextXml.getBean("student");
        System.out.println("Xml Config: " + studentXml);
    }
    public static void annotationConfig(){
        ApplicationContext contextAnnotation = new ClassPathXmlApplicationContext("SpringAnnotation.xml");
        org.genspark.Annotation.Student studentAnnotation = (org.genspark.Annotation.Student) contextAnnotation.getBean("student");
        System.out.println("Annotation Config: " + studentAnnotation);
    }
    public static void JavaConfig(){
        ApplicationContext contextJava = new AnnotationConfigApplicationContext(AppConfig.class);
        org.genspark.Java.Student studentJava = contextJava.getBean(org.genspark.Java.Student.class);
        System.out.println("Java Config: " + studentJava);

    }
}
