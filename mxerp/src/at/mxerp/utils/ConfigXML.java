package at.mxerp.utils;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;

import org.eclnt.jsfserver.util.parse.SAXParserCreator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigXML {
  static String user;
  public static String getUser() {return user;}
  static String pass;
  public static String getPass() {return pass;}
  static String version;
  public static String getVersion() {return version;}

  static {
    try {
      InputStream is = Helper.getResourceAsStream("app.cfg.xml");
      SAXParser parser = SAXParserCreator.createSAXParser();
      InputSource source = new InputSource(is);
      MyParser mp = new MyParser();
      parser.parse(source, mp);
    } catch (Throwable t) {}
  }

  static class MyParser extends DefaultHandler {

    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
      if (name.equals("connection")) {
        user = attributes.getValue("user");
        pass = attributes.getValue("pass");
      }  else if("info".equals(name)) {
         version = attributes.getValue("version");
      }
    }
  }
}
