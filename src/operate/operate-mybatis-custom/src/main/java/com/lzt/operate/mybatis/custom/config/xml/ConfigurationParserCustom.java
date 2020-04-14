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

	private List<String> warnings;
	private List<String> parseErrors;
	private Properties extraProperties;

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

		this.parseErrors = new ArrayList<>();
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
		this.parseErrors.clear();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new ParserEntityResolver());

			ParserErrorHandler handler = new ParserErrorHandler(this.warnings, this.parseErrors);

			builder.setErrorHandler(handler);

			Document document = null;
			try {
				document = builder.parse(inputSource);
			} catch (SAXParseException e) {
				throw new XMLParserException(this.parseErrors);
			} catch (SAXException e) {
				if (e.getException() == null) {
					this.parseErrors.add(e.getMessage());
				} else {
					this.parseErrors.add(e.getException().getMessage());
				}
			}

			if (this.parseErrors.size() > 0) {
				throw new XMLParserException(this.parseErrors);
			}

			Element rootNode = document.getDocumentElement();
			Configuration config = parseMyBatisGeneratorConfiguration(rootNode);

			if (this.parseErrors.size() > 0) {
				throw new XMLParserException(this.parseErrors);
			}

			return config;
		} catch (ParserConfigurationException e) {
			this.parseErrors.add(e.getMessage());
			throw new XMLParserException(this.parseErrors);
		}
	}

	private Configuration parseMyBatisGeneratorConfiguration(Element rootNode) throws XMLParserException {

		//替换MyBatisGeneratorConfigurationParser
		MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParserCustom(
				this.extraProperties);

		return parser.parseConfiguration(rootNode);
	}

}