package priv.cai.jobapply.dataload.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import priv.cai.jobapply.constant.Constants;

public class SaveTXTFiles {
	
	public static int saveAsTextFiles(StringBuffer sb) {
		if(StringUtils.isEmpty(sb)) return 0;
		
		BufferedWriter writer = null;
        try {
            String timeLog = new SimpleDateFormat(Constants.DATAFORMAT_YMDHMS).format(new Date());
            File logFile = new File(Constants.FILE_NAME+timeLog);

            //System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            String str = sb.toString().replaceAll("\\s+"," ");
            
            writer.write(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            	e.printStackTrace();
            	writer = null;
            	return 0;
            }
        }
		
		return 1;
	}

	public static void main(String[] args) {
		//Spider sp = new Spider();
		//System.out.print(saveTextFiles(sp.getContext("https://www.indeed.com/xxx")));
	}

}
