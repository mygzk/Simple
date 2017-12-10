package com.example.simple.xmlPare;

import android.content.res.XmlResourceParser;

import com.example.simple.MyApp;
import com.example.simple.R;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by guozhk on 2017/12/4.
 */

public class PullPraserHelp {


    private List<Channel> getChannelList() throws ParserConfigurationException, SAXException, IOException
    {
        //实例化一个SAXParserFactory对象
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser;
        //实例化SAXParser对象，创建XMLReader对象，解析器
        parser=factory.newSAXParser();
        XMLReader xmlReader=parser.getXMLReader();
        //实例化handler，事件处理器
        SAXPraserHelper helperHandler=new SAXPraserHelper();
        //解析器注册事件
        xmlReader.setContentHandler(helperHandler);
        //读取文件流
        InputStream stream= MyApp.getApp().getResources().openRawResource(R.xml.channels);
        InputSource is=new InputSource(stream);
        //解析文件
        xmlReader.parse(is);
        return helperHandler.getList();
    }


    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        XmlResourceParser xrp =MyApp.getApp().getResources().getXml(R.xml.channels);

        try {
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("item")) {
                        Map<String, String> map = new HashMap<String, String>();
                        String id = xrp.getAttributeValue(null, "id");// 通过属性名来获取属性值
                        map.put("id", id);
                        String url = xrp.getAttributeValue(1);// 通过属性索引来获取属性值
                        map.put("url", url);
                        map.put("name", xrp.nextText());
                        list.add(map);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }
}
