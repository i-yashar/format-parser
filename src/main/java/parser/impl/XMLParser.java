package parser.impl;

import parser.FormatParser;
import parser.enums.Format;

import javax.xml.bind.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class XMLParser implements FormatParser {
    private Map<String, Marshaller> marshallers = new HashMap<>();
    private Map<String, Unmarshaller> unmarshallers = new HashMap<>();
    private boolean prettyPrint;

    @Override
    public String serialize(Object object) {
        try {
            StringWriter sw = new StringWriter();

            this.getMarshaller(object).marshal(object, sw);

            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void serialize(Object object, String fileName) {
        this.serialize(object, new File(PATH + fileName));
    }

    @Override
    public void serialize(Object object, File file) {
        try (FileWriter fw = new FileWriter(file)) {

            this.getMarshaller(object).marshal(object, fw);

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T deserialize(String fileName, Class<T> type) {
        return this.deserialize(new File(PATH + fileName), type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(File file, Class<T> type) {
        T result = null;

        try {
            Unmarshaller unmarshaller = this.getUnmarshaller(type);
            result = (T) unmarshaller.unmarshal(new FileInputStream(file));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void setPrettyPrint() {
        this.prettyPrint = true;

        for (String key : marshallers.keySet()) {
            try {
                this.marshallers.get(key).setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeFormat(Format format) {
    }

    private Marshaller getMarshaller(Object obj) throws JAXBException {

        if (this.marshallers.containsKey(obj.getClass().getName())) {
            return this.marshallers.get(obj.getClass().getName());
        }

        Marshaller marshaller;

        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        marshaller = jaxbContext.createMarshaller();
        this.marshallers.put(obj.getClass().getName(), marshaller);
        this.unmarshallers.put(obj.getClass().getName(), jaxbContext.createUnmarshaller());

        if(this.prettyPrint) {
            this.setPrettyPrint();
        }

        return marshaller;
    }

    private Unmarshaller getUnmarshaller(Class<?> clazz) throws JAXBException {

        if (this.unmarshallers.containsKey(clazz.getName())) {
            return this.unmarshallers.get(clazz.getName());
        }

        Unmarshaller unmarshaller;

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        unmarshaller = jaxbContext.createUnmarshaller();
        this.unmarshallers.put(clazz.getName(), unmarshaller);
        this.marshallers.put(clazz.getName(), jaxbContext.createMarshaller());

        if(this.prettyPrint) {
            this.setPrettyPrint();
        }

        return unmarshaller;
    }
}







