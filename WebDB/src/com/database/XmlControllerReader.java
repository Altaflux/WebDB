package com.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XmlControllerReader {

	static final String SERVERTYPENAME = "servertypeName";
	static final String CONTROLLERLOCATION = "controllerlocation";
	static final String JDBCLOCATION = "jdbcLocation";
	static final String CONTROLLER = "controller";

	public List<AdapterInfo> readConfig(String configFile) {
		List<AdapterInfo> adapters = new ArrayList<AdapterInfo>();
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));

		try{
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			AdapterInfo adapter = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();


				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart() == (CONTROLLER)) {
						adapter = new AdapterInfo();
						Iterator<Attribute> attributes = startElement.getAttributes();

						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(SERVERTYPENAME)) {
								adapter.setServerTypeName(attribute.getValue());
							}
						}
					}
					if (event.isStartElement()) {

						if (event.asStartElement().getName().getLocalPart().equals(CONTROLLERLOCATION)) {
							event = eventReader.nextEvent();
							adapter.setControllerLocation(event.asCharacters().getData());
							continue;
						}
					}

					if (event.asStartElement().getName().getLocalPart().equals(JDBCLOCATION)) {
						event = eventReader.nextEvent();
						adapter.setJdbcLocation(event.asCharacters().getData());
						continue;
					}
				}

				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (CONTROLLER)) {
						adapters.add(adapter);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return adapters;
	}

}
