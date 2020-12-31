package com.company;

import okhttp3.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        String xpathEl = "xpath";
        String xmlUrl = "url";

        OkSing.getInstance()
                .newCall(RequestSingleton.getInstance().url(xmlUrl).build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String resp = response.body().string();
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = null;
                        try {
                            db = dbf.newDocumentBuilder();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                        try {
                            XPath xpath = XPathFactory.newInstance().newXPath();
                            org.w3c.dom.Document xmlDoc = db.parse(new InputSource(new ByteArrayInputStream(resp.getBytes("UTF-8"))));
                            XPathExpression name = xpath.compile(xpathEl);
                            NodeList nodeList = (NodeList) name.evaluate(xmlDoc, XPathConstants.NODESET);
                            for (int i = 0; i < nodeList.getLength(); i++) {
                                Node node = nodeList.item(i);
                                OkSing.getInstance().newCall(RequestSingleton.getInstance().url(node.getTextContent()).build()).execute().body().string();
                            }
                        } catch (SAXException | XPathExpressionException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
