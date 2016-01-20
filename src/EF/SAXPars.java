package EF;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class SAXPars extends DefaultHandler {


    String thisElement = "";
    String Host = "";
    String Username = "";
    String Password = "";




    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("HOST")) {
            setHost(new String(ch, start, length));

        }
        if (thisElement.equals("USERNAME")) {
            setUsername(new String(ch, start, length));
        }
        if (thisElement.equals("name")) {
            setPassword(new String(ch, start, length));
        }

    }



    public void setHost (String Host){
        this.Host = Host;
    }
    public void setUsername (String Username){
        this.Username = Username;
    }
    public void setPassword (String Password){
        this.Password = Password;
    }

    public String getHost (){
        return Host;
    }
    public String getUsername (){
        return Username;
    }
    public String getPassword (){
        return Password;
    }

}
