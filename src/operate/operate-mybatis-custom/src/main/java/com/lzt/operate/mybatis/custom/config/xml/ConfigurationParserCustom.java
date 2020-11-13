package com.lzt.operate.mybatis.custom.config.xml;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.config.xml.MyBatisGeneratorConfigurationParser;
import org.mybatis.generator.config.xml.ParserEntityResolver;
import org.mybatis.generator.config.xml.ParserErrorHandler;
import org.mybatis.generator.exception.XMLParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigurationParserCustom extends ConfigurationParser {

	private final List<String> warnings;
	private final List<String> parseErrors;
	private final Properties extraProperties;

	public ConfigurationParserCustom(List<String> warnings) {
		this(null, warnings);
	}

	public ConfigurationParserCustom(Properties extraProperties, List<String> warnings) {
		super(extraProperties, warnings);
		this.extraProperties = extraProperties;

		if (warnings == null) {
			this.warnings = new ArrayList<>();
		} else {
			this.warnings = warnings;
		}

		parseErrors = new ArrayList<>();
	}

	@Override
	public Configuration parseConfiguration(File inputFile) throws IOException, XMLParserException {
		FileReader fr = new FileReader(inputFile);

		return parseConfiguration(fr);
	}

	@Override
	public Configuration parseConfiguration(InputStream inputStream) throws IOException, XMLParserException {
		InputSource is = new InputSource(inputStream);

		return parseConfiguration(is);
	}

	@Override
	public Configuration parseConfiguration(Reader reader) throws IOException, XMLParserException {
		InputSource is = new InputSource(reader);

		return parseConfiguration(is);
	}

	private Configuration parseConfiguration(InputSource inputSource) throws IOException, XMLParserException {
		parseErrors.clear();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new ParserEntityResolver());

			ParserErrorHandler handler = new ParserErrorHandler(warnings, parseErrors);

			builder.setErrorHandler(handler);

			Document document = null;
			try {
				document = builder.parse(inputSource);
			} catch (SAXParseException e) {
				throw new XMLParserException(parseErrors);
			} catch (SAXException e) {
				if (e.getException() == null) {
					parseErrors.add(e.getMessage());
				} else {
					parseErrors.add(e.getException().getMessage());
				}
			}

			if (parseErrors.size() > 0) {
				throw new XMLParserException(parseErrors);
			}

			Element rootNode = document.getDocumentElement();
			Configuration config = parseMyBatisGeneratorConfiguration(rootNode);

			if (parseErrors.size() > 0) {
				throw new XMLParserException(parseErrors);
			}

			return config;
		} catch (ParserConfigurationException e) {
			parseErrors.add(e.getMessage());
			throw new XMLParserException(parseErrors);
		}
	}

	private Configuration parseMyBatisGeneratorConfiguration(Element rootNode) throws XMLParserException {

		//替换MyBatisGeneratorConfigurationParser
		MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParserCustom(
				extraProperties);

		return parser.parseConfiguration(rootNode);
	}

}