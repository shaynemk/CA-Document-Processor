package taytom258.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import taytom258.core.DirHandler;
import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;
import taytom258.lib.Strings;

public class ConfigHandler{
	
	private static Properties prop = new Properties();
	private static File dir = new File(Config.CONFIG_PATH);
	private static File file = new File(Config.CONFIG_PATH+"\\"+Strings.CONFIG_NAME);
	
	public static void init(){
		if(!dir.exists()){
			try {
				DirHandler.createUserDir(Reference.AUTHOR);
			} catch (IOException e) {
				e.printStackTrace();
				LogHelper.warning("Unable to create root directory");
			}
			LogHelper.debug("Root directory created");
		}else{
			LogHelper.debug("Root directory exists");
		}
		try{
			load();
		}catch(IOException e){
			LogHelper.warning("Config not found: creating default config (This is normal on a first start)");
			createDefaults();
		}
		LogHelper.debug("Config handler initilized");
	}
	
	private static void load() throws IOException{
		
		InputStream is = new FileInputStream(file);
		
		try {
			prop.load(is);
			
			Config.chfPath = prop.getProperty(Config.CONFIG_NAMES[0]);
			Config.chfTest = prop.getProperty(Config.CONFIG_NAMES[1]);
			Config.useChf = Boolean.valueOf(prop.getProperty(Config.CONFIG_NAMES[2]));
			Config.autoSelection = Boolean.valueOf(prop.getProperty(Config.CONFIG_NAMES[3]));
			Config.debug = Boolean.valueOf(prop.getProperty(Config.CONFIG_NAMES[4]));
			
			LogHelper.debug("Config loaded");
			
			is.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void save(){
		
//		LogHelper.debug(file.getAbsolutePath());
//		file.delete();
		
		try {
			OutputStream os = new FileOutputStream(file);
			
			prop.setProperty(Config.CONFIG_NAMES[0], Config.chfPath);
			prop.setProperty(Config.CONFIG_NAMES[1], Config.chfTest);
			prop.setProperty(Config.CONFIG_NAMES[2], String.valueOf(Config.useChf));
			prop.setProperty(Config.CONFIG_NAMES[3], String.valueOf(Config.autoSelection));
			prop.setProperty(Config.CONFIG_NAMES[4], String.valueOf(Config.debug));
			
			prop.store(os, Config.CONFIG_HEADER);
			
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void createDefaults(){
		
//		file.delete();
		
		Config.chfPath = Config.CHF_PATH;
		Config.chfTest = Config.CHF_TEST;
		Config.useChf = Config.USE_CHF;
		Config.autoSelection = Config.AUTO_SELECTION;
		Config.debug = Config.DEBUG;
		
		LogHelper.debug("Defaults saved");
		
		save();
	}
}
