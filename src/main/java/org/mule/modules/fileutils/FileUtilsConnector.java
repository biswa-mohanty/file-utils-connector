package org.mule.modules.fileutils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.FileUtils;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.lifecycle.OnException;
import org.mule.modules.fileutils.error.ErrorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Connector(name="file-utils", friendlyName="FileUtils")
@MetaDataScope( DataSenseResolver.class )
@OnException(handler=ErrorHandler.class)
public class FileUtilsConnector {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileUtilsConnector.class);
    
	@Processor
	/*public String locateFiles (String directoryPath, String pattern) throws IOException {
		LOG.debug("Received Source Directory Path: {}", directoryPath);
		LOG.debug("Received Patterns Set: {}", pattern);
		File dir = new File(directoryPath);
		FileFilter fileFilter = new WildcardFileFilter(pattern);
		File[] files = dir.listFiles(fileFilter);
		StringBuilder fileNames = new StringBuilder();
		//List<String> fileNames = new ArrayList<>();
		for (File file: files) {
			fileNames.append(file.getName());
			fileNames.append(" ");
		}
		return fileNames.toString();
	}*/
	public List<String> checkIfFilesExists (String directoryPath, String pattern) throws IOException {
		LOG.debug("Received Source Directory Path: {}", directoryPath);
		LOG.debug("Received Patterns Set: {}", pattern);
		File dir = new File(directoryPath);
		FileFilter fileFilter = new WildcardFileFilter(pattern);
		File[] files = dir.listFiles(fileFilter);
		List<String> fileNames = new ArrayList<>();
		for (File file: files) {
			fileNames.add(file.getAbsolutePath());
		}
		return fileNames;
	}
	
	@Processor
	//public void copyFilesDirectly (String sourceFileName, String sourceDirectoryPath, String targetFileName, String targetDirectoryPath) throws IOException {
	public void copyFilesDirectly (String sourceDirectoryPath, String targetDirectoryPath) throws IOException {
		String targetPath;
		//LOG.debug("Received Source FileName: {}", sourceFileName);
		LOG.debug("Received Source Directory Path: {}", sourceDirectoryPath);
		//LOG.debug("Received Target FileName: {}", targetFileName);
		LOG.debug("Received Target Directory Path: {}", targetDirectoryPath);
		//LOG.debug("File separtor of the platform: {}", File.separator);
		
		int indexOfSeparator = sourceDirectoryPath.lastIndexOf(File.separator);
		String fileName = sourceDirectoryPath.substring(indexOfSeparator);
		targetPath = targetDirectoryPath + File.separator + fileName;
		
		File sourceFile = new File(sourceDirectoryPath);
		File targetFile = new File(targetPath);
		FileUtils.copyFile(sourceFile, targetFile);
		
		/*if (sourceDirectoryPath.substring(sourceDirectoryPath.length() - 1) == File.separator) {
			sourcePath = sourceDirectoryPath + sourceFileName;
		} else {
			sourcePath = sourceDirectoryPath + File.separator + sourceFileName;
		}*/
		
		
		
	}
	
	//@Config
    //ConnectorConfig config;

    /**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     */
    /*@Processor
    public String greet(String friend) {
        
         * MESSAGE PROCESSOR CODE GOES HERE
         
        return config.getGreeting() + " " + friend + ". " + config.getReply();
    }

    *//**
     * DataSense processor

     * @param key Key to be used to populate the entity
     * @param entity Map that represents the entity
     * @return Some string
     *//*
    @Processor
    public Map<String,Object> addEntity( @MetaDataKeyParam String key, @Default("#[payload]") Map<String,Object> entity) {
        
         * USE THE KEY AND THE MAP TO DO SOMETHING
         
        return entity;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }*/

}