package at.mxerp.managedbeans.pojo.form;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CCFormField {
  enum Type {
    STRING, INTEGER
  }

  int order() default 0 ;
  Type type() default Type.STRING;
  String component() default "t:label";
  String value();
  String title();
}
