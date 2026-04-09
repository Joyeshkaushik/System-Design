interface IReports{
    public String getJsonData(String data);
}
class XmlDataProvider{
    String getXmlData(String data){
         int sep = data.indexOf(':');
        String name = data.substring(0, sep);
        String id   = data.substring(sep + 1);
        // Build an XML representation
        return "<user>"
                + "<name>" + name + "</name>"
                + "<id>"   + id   + "</id>"
                + "</user>";
    }
    
}
class XmlDataproviderAdapter implements IReports{
    XmlDataProvider provider;
    XmlDataproviderAdapter(XmlDataProvider provider){
        this.provider=provider;
    }
    public String getJsonData(String data){
        String xml = provider.getXmlData(data);
         int startName = xml.indexOf("<name>") + 6;
        int endName   = xml.indexOf("</name>");
        String name   = xml.substring(startName, endName);

        int startId = xml.indexOf("<id>") + 4;
        int endId   = xml.indexOf("</id>");
        String id    = xml.substring(startId, endId);

        //  Build and return JSON
        return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
    }


}
class Client {
    public void getReport(IReports report, String rawData) {
        System.out.println("Processed JSON: "
            + report.getJsonData(rawData));
    }
}

public class AdapterDesignPattern {
    public static void main(String[] args) {
         // 1. Create the adaptee
        XmlDataProvider xmlProv = new XmlDataProvider();

        // 2. Make our adapter
        IReports adapter = new XmlDataproviderAdapter(xmlProv);

        // 3. Give it some raw data
        String rawData = "Alice:42";

        // 4. Client prints the JSON
        Client client = new Client();

        client.getReport(adapter, rawData);
        // → Processed JSON: {"name":"Alice", "id":42}
    }
}
